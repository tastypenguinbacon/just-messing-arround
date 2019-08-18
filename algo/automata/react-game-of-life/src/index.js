import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

const initialState = []

const ROW_CNT = 200
const COL_CNT = 200

for (let i = 0; i < ROW_CNT; i++) {
  initialState.push([])
  for (let j = 0; j < COL_CNT; j++) {
    if (Math.random() >= 0.5) {
      initialState[i].push(true)
    } else {
      initialState[i].push(false)
    }
  }
}

ReactDOM.render(
  <App initialState={initialState}/>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.register();
