import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-1.5, 4.5, 2000)
y = np.linspace(2, -2, 1500)

c = np.repeat(np.array([x]), y.size, axis=0) + np.repeat(np.array([y]), x.size, axis=0).T * 1j
c = 1 / c
z = np.zeros(c.shape, dtype=np.complex)
img = np.zeros(c.shape)

for i in range(128):
    z = z ** 2 + c
    img[np.absolute(z) < 2] += 1

plt.imshow(np.sqrt(img))
plt.show()

