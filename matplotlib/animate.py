import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np


class RandomWalk:
    def __init__(self):
        self.position = [0]
        self.time = [0]
        
    def __call__(self):
        self.position.append(self.position[-1] + np.random.normal(0.2, 10.0))
        self.time.append(self.time[-1] + 1)
        return self.time, self.position


class Animation:
    def __init__(self, ax):
        self.ax = ax
        self.rw = [RandomWalk() for _ in range(100)]
        
    def __call__(self, i):
        xs, ys = [], []
        for rw in self.rw:
            x, y = rw()
            xs.append(x)
            ys.append(y)
        self.ax.clear()
        xs = np.average(np.array(xs), axis=0)
        ys = np.array(ys)
        e = np.std(ys, axis=0)
        ys = np.average(ys, axis=0)
        self.ax.plot(xs, ys, 'r')
        self.ax.fill_between(x, ys + e, ys - e, facecolor='r', alpha=0.3)


fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)
anim = FuncAnimation(fig, Animation(ax), interval=10)
plt.show()


