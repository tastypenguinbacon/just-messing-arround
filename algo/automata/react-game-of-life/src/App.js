import React from 'react'
import './App.css'
import _ from 'lodash'

function* neighboursOfCell({row, col}, {rowCnt, colCnt}) {
    for (let i = -1; i <= 1; i++) {
        for (let j = -1; j <= 1; j++) {
            const temp_row = (row + i + rowCnt) % rowCnt
            const temp_col = (col + j + colCnt) % colCnt
            if ((i !== 0) || (j !== 0)) {
                yield {row: temp_row, col: temp_col}
            }
        }
    }
}

function nextState(aliveCells, callback) {
    const [rowCnt, colCnt] = [aliveCells.length, aliveCells[0].length]
    const isReachable = ({row, col}) => row >= 0 && row < rowCnt && col >= 0 && col < colCnt
   
    const aliveCellsAsList = 
        _.range(rowCnt * colCnt)
            .map(i => ({row: (i - i % colCnt) / rowCnt, col: i % colCnt}))
            .filter(({row, col}) => aliveCells[row][col])

    let neighboursOfAliveCells =
        aliveCellsAsList.flatMap(aliveCell => [...neighboursOfCell(aliveCell, {rowCnt, colCnt})])

    let toConsider = _.uniqBy(_.concat(aliveCells, neighboursOfAliveCells),
        ({row, col}) => row * rowCnt + col)

    let aliveNeighbours = toConsider.map(cell => {
        let aliveNeighbours = [...neighboursOfCell(cell, {rowCnt, colCnt})]
            .filter(({row, col}) => aliveCells[row] && aliveCells[row][col])
            .length
        return {aliveNeighbours, cell}
    })

    let nextAlive = aliveNeighbours.map(({cell, aliveNeighbours}) => {
        if (aliveCells[cell.row] && aliveCells[cell.row][cell.col]) {
            return aliveNeighbours === 3 || aliveNeighbours === 2 ? cell : null
        } else {
            return aliveNeighbours === 3 ? cell : null
        }
    }).filter(i => i !== null).filter(isReachable)

    let nextAliveCells = _.range(rowCnt).map(i => _.range(colCnt).map(j => false))
    nextAlive.forEach(({row, col}) => nextAliveCells[row][col] = true)

    callback(nextAliveCells)
}

function Row(props) {
    const {aliveCells} = props
    return (
        <div className="columns">
            {aliveCells
                .map(alive => alive ? 'alive' : 'dead')
                .map(i => <div className={i}/>)}
        </div>
    )
}

class App extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            aliveCells: props.initialState
        }
    }

    render() {
        const {aliveCells} = this.state 
        return (
            <div className="rows">
                {aliveCells.map(row => <Row aliveCells={row} />)}
            </div>)
    }

    componentDidMount() {
        setInterval(() => {
            nextState(this.state.aliveCells, nextCells => this.setState({aliveCells: nextCells}))
        }, 100)
    }
}

export default App;
