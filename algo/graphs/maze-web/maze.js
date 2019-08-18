ROW_CNT = 100
COL_CNT = 150

function* neighboursOfCell({row, col}) {
    for (let i = -1; i <= 1; i++) {
        for (let j = -1; j <= 1; j++) {
            const temp_row = row + i, temp_col = col + j
            if ((i === 0) ^ (j === 0)) {
                yield {row: temp_row, col: temp_col}
            }
        }
    }
}

function setCellToState({row, col}, state) {
    const selector =  `#maze .rows .columns:eq(${row}) div:eq(${col})`
    $(selector)
        .removeClass('wall')
        .removeClass('considered')
        .removeClass('corridor')
        .addClass(state)
}

function isReachableCell({row, col}) {
    if (row <= 0 || col <= 0) {
        return false
    } else if (row >= ROW_CNT - 1 || col >= COL_CNT - 1) {
        return false
    } else {
        return true
    }
}

function isWall({row, col}) {
    const selector =  `#maze .rows .columns:eq(${row}) div:eq(${col})`
    return $(selector).hasClass('wall') || $(selector).hasClass('considered')
}

function getBehindWall(cell, wall) {
    nxt = {
        row: 2 * wall.row - cell.row,
        col: 2 * wall.col - cell.col
    }
    if (!isReachableCell(nxt)) {
        return null
    } else {
        return nxt
    }
}


const startingCell = {
    row: (ROW_CNT - ROW_CNT % 2) / 2,
    col: (COL_CNT - COL_CNT % 2) / 2
}

const walls = Array.from(_.map([...neighboursOfCell(startingCell)], (neighbour) => {
    return {cell: startingCell, wall: neighbour}
}))

$(document).ready(() => {
    const rows = $('<div class="rows"></div>')
    for (let i = 0; i < ROW_CNT; i++) {
        const columns = $('<div class="columns"></div>')
        for (let j = 0; j < COL_CNT; j++) {
            columns.append($('<div class="wall"></div>'))
        }
        rows.append(columns)
    }
    $('#maze').append(rows)
    window.requestAnimationFrame(initializeStep)
})

function initializeStep() {
    setCellToState(startingCell, 'corridor')
    for (let {wall} of walls) {
        if (isReachableCell(wall)) {
            setCellToState(wall, 'considered')
        }
    }
    window.requestAnimationFrame(mazeGenerationStep)
}

function mazeGenerationStep() {
    if (_.isEmpty(walls)) {
        return
    }
    [rand_idx, {cell, wall}] = _.sample(_.zip(_.range(walls.length), walls))
    if (nxt = getBehindWall(cell, wall)) {
        if (isWall(nxt)) {
            setCellToState(nxt, 'corridor')
            setCellToState(wall, 'corridor')
            for (let nxtWall of neighboursOfCell(nxt)) {
                if (isWall(nxtWall) && isReachableCell(nxtWall)) {
                    setCellToState(nxtWall, 'considered')
                    walls.push({cell: nxt, wall: nxtWall})
                }
            }
        } else {
            setCellToState(wall, 'wall')
        }
    } else {
        setCellToState(wall, 'wall')
    }
    walls.splice(rand_idx, 1)
    window.requestAnimationFrame(mazeGenerationStep)
}
