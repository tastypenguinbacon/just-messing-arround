import numpy as np
import matplotlib.pyplot as plt
from argparse import ArgumentParser

parser = ArgumentParser()
parser.add_argument('-minRe', type=float, default=-1.41)
parser.add_argument('-maxRe', type=float, default=-1.4075)
parser.add_argument('-minIm', type=float, default=0.135)
parser.add_argument('-maxIm', type=float, default=0.1375)
parser.add_argument('-sharpness', type=int, default=2000)
args = parser.parse_args()

tan = abs(args.maxIm - args.minIm) / abs(args.maxRe - args.minRe)

if tan <= 1:
    x_elem_cnt = args.sharpness
    y_elem_cnt = int(x_elem_cnt * tan)
else:
    y_elem_cnt = args.sharpness
    x_elem_cnt = int(y_elem_cnt / tan)


x = np.linspace(args.minRe, args.maxRe, x_elem_cnt)
y = np.linspace(args.maxIm, args.minIm, y_elem_cnt)

c = np.repeat(np.array([x]), y.size, axis=0) + np.repeat(np.array([y]), x.size, axis=0).T * 1j
z = np.zeros(c.shape, dtype=np.complex)
img = np.zeros(c.shape)

for i in range(128):
    z = z ** 2 + c
    img[np.absolute(z) < 2] += 1

plt.imshow(np.sqrt(img))
plt.show()

