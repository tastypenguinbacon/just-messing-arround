ROW_CNT = 75
COL_CNT = 75

function* neighboursOfCell({row, col}) {
    for (let i = -1; i <= 1; i++) {
        for (let j = -1; j <= 1; j++) {
            const temp_row = row + i, temp_col = col + j
            if ((i !== 0) || (j !== 0)) {
                yield {row: temp_row, col: temp_col}
            }
        }
    }
}

function setCellToState({row, col}, state) {
    const selector =  `#gol .rows .columns:eq(${row}) div:eq(${col})`
    const cell = $(selector)
    cell.removeClass('dead').removeClass('alive').addClass(state)
}

function isReachable({row, col}) {
    return row >= 0 && row < ROW_CNT && col >= 0 && col < COL_CNT
}
var aliveTree = {}

function setAliveTree({row, col}, value) {
    if (!aliveTree[row]) {
        aliveTree[row] = {}
    }
    aliveTree[row][col] = value
}

var aliveCells = []

$(document).ready(() => {
    const rows = $('<div class="rows"></div>')
    for (let row = 0; row < ROW_CNT; row++) {
        const columns = $('<div class="columns"></div>')
        for (let col = 0; col < COL_CNT; col++) {
            if (Math.random() >= 0.5) {
                columns.append($('<div class="alive"></div>'))
                aliveCells.push({row, col})
                setAliveTree({row, col}, true)
            } else {
                columns.append($('<div class="dead"></div>'))
                setAliveTree({row, col}, false)
            }
        }
        rows.append(columns)
    }
    $('#gol').append(rows)
    console.time('draw')
    window.requestAnimationFrame(iterateGameOfLife)
})

function iterateGameOfLife() {
    console.timeEnd('draw')
    console.time('iteration')
    let neighboursOfAliveCells = 
           _.flatMap(aliveCells, (aliveCell) => [...neighboursOfCell(aliveCell)])
    let toConsider = _.uniqBy(
        _.concat(aliveCells, neighboursOfAliveCells), 
        ({row, col}) => row * ROW_CNT + col)

    let aliveNeighbours = _.map(toConsider, (cell) => {
        let aliveNeighbours = 0
        for (let {row, col} of neighboursOfCell(cell)) {
            if (aliveTree[row] && aliveTree[row][col]) {
                aliveNeighbours++
            }
        }
        return {aliveNeighbours, cell}
    })

    let aliveOrDead = _.map(aliveNeighbours, ({cell, aliveNeighbours}) => {
        if (aliveTree[cell.row] && aliveTree[cell.row][cell.col]) {
            return aliveNeighbours === 3 || aliveNeighbours == 2
                ? {alive: true, cell}
                : {alive: false, cell}
        } else {
            return aliveNeighbours === 3 
                ? {alive: true, cell}
                : {alive: false, cell}
        }
    })

    let nextAlive = _.filter(_.map(_.filter(aliveOrDead, c => c.alive), c => c.cell), isReachable)
    let nextDead = _.filter(_.map(_.filter(aliveOrDead, c => !c.alive), c => c.cell), isReachable)
    console.timeEnd('iteration')

    draw(nextAlive, nextDead)

    aliveCells = nextAlive
    window.requestAnimationFrame(iterateGameOfLife)
    console.time('draw')
}

function draw(nextAlive, nextDead) { 
    console.time('jquery-queries')
    let cellsChanged = 0
    for (let cell of nextAlive) {
        if (!(aliveTree[cell.row] && aliveTree[cell.row][cell.col])) {
            setCellToState(cell, 'alive')
            cellsChanged++
        }
        setAliveTree(cell, true)
    }
    for (let cell of nextDead) {
        if (!(aliveTree[cell.row] && !aliveTree[cell.row][cell.col])) {
            setCellToState(cell, 'dead')
            cellsChanged++
        }
        setAliveTree(cell, false)
    }
    console.timeEnd('jquery-queries')
    console.log({cellsChanged})
}
