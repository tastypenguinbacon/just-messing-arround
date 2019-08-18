import React from 'react';
import './App.css';
import _ from 'lodash'

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

function nextState(aliveCells) {
  const aliveCellsAsList = []

  const [rowCnt, colCnt] = [aliveCells.length, aliveCells[0].length]
  const isReachable = ({row, col}) => row >= 0 && row < rowCnt && col >= 0 && col < colCnt

  for (let row = 0; row < rowCnt; row++) {
      for (let col = 0; col < colCnt; col++) {
          if (aliveCells[row][col]) {
              aliveCellsAsList.push({row, col})
          }
      }
  }

  let neighboursOfAliveCells =
       _.flatMap(aliveCellsAsList, (aliveCell) => [...neighboursOfCell(aliveCell)])
  let toConsider = _.uniqBy(_.concat(aliveCells, neighboursOfAliveCells),
      ({row, col}) => row * rowCnt + col)

  let aliveNeighbours = _.map(toConsider, (cell) => {
      let aliveNeighbours = 0
      for (let {row, col} of neighboursOfCell(cell)) {
          if (aliveCells[row] && aliveCells[row][col]) {
              aliveNeighbours++
          }
      }
      return {aliveNeighbours, cell}
  })

  let aliveOrDead = _.map(aliveNeighbours, ({cell, aliveNeighbours}) => {
      if (aliveCells[cell.row] && aliveCells[cell.row][cell.col]) {
          return aliveNeighbours === 3 || aliveNeighbours === 2
              ? {alive: true, cell}
              : {alive: false, cell}
      } else {
          return aliveNeighbours === 3
              ? {alive: true, cell}
              : {alive: false, cell}
      }
  })

  let nextAlive = _.filter(_.map(_.filter(aliveOrDead, c => c.alive), c => c.cell), isReachable)
  let nextAliveCells = []
  for (let i = 0; i < rowCnt; i++) {
      nextAliveCells[i] = []
      for (let j = 0; j < colCnt; j++) {
          nextAliveCells[i][j] = false
      }
  }
  for (let {row, col} of nextAlive) {
      nextAliveCells[row][col] = true
  }

  return nextAliveCells
}


class Row extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      aliveCells: props.aliveCells
    }
  }

  render() {
    const columns = []
    const {aliveCells} = this.state
    console.log(_.sum(aliveCells))
    let key = 0
    for (let cellIsAlive of aliveCells) {
      if (cellIsAlive) {
        columns.push(<div className='alive' key={key++}/>)
      } else {
        columns.push(<div className='dead' key={key++}/>)
      }
    }

    return (
      <div className="columns">
        {columns}
      </div>
    )
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      aliveCells: nextProps.aliveCells
    })
  }
}

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      aliveCells: props.initialState
    }
  }

  render() {
    const rows = []
    const {aliveCells} = this.state
    let key = 0
    for (let row of aliveCells) {
      rows.push(<Row aliveCells={[...row]} key={key++}/>)
    }
    return (
      <div className="rows">
        {rows}
      </div>)
  }

  componentDidMount() {
    setInterval(() => {
      this.setState({
        aliveCells: nextState(this.state.aliveCells)
      })
    }, 100)
  }
}

export default App;
