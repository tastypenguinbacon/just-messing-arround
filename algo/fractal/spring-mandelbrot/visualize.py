import matplotlib.pyplot as plt
import numpy as np
import sys

lines = []

with open(sys.argv[1]) as f:
    for line in f:
        l = []
        for i in map(int, line.split()):
            l.append(i)
        if l:
            lines.append(l)

line_width = max([len(i) for i in lines])
for i, l in enumerate(lines[:]):
    if len(l) != line_width:
        lines[i] = lines[i] + [0] * (line_width - len(l))

print(min([len(i) for i in lines]))

plt.imshow(np.array(lines, dtype=np.float32) / 255)
plt.show()
