import numpy as np
from scipy.signal import convolve2d

import matplotlib.pyplot as plt
from matplotlib import animation, rc

def gols(x):
    conv_mask = np.array([
        [1, 1, 1],
        [1, 9, 1],
        [1, 1, 1]
    ],dtype=np.int8)
    while True:
        yield x
        x = convolve2d(x, conv_mask, mode='same')
        alive = np.logical_or(x == 3, np.logical_or(x == 11, x == 12))
        x[alive], x[~alive] = 1, 0

width, height = 256, 256
x = np.random.randint(2, size=width*height, dtype=np.int8).reshape((height, width))

fig = plt.figure()
ax1 = fig.add_subplot(1, 1, 1)

steps = gols(x)

def animate(i):
    ax1.clear()
    current_state = next(steps)
    ax1.imshow(current_state, cmap='gray', vmin=0, vmax=1)

anim = animation.FuncAnimation(fig, animate, interval=50)
plt.show()
