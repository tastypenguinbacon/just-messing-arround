import numpy as np
import matplotlib.pyplot as plt
from argparse import ArgumentParser
import tensorflow as tf

parser = ArgumentParser()
parser.add_argument('-minRe', type=float, default=-1.41)
parser.add_argument('-maxRe', type=float, default=-1.4075)
parser.add_argument('-minIm', type=float, default=0.135)
parser.add_argument('-maxIm', type=float, default=0.1375)
parser.add_argument('-sharpness', type=int, default=2000)
parser.add_argument('-steps', type=int, default=128)
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

C = tf.constant(c, dtype=tf.complex64)
X = tf.placeholder("complex64", [y_elem_cnt, x_elem_cnt], name="X")

_, _, Y = tf.while_loop(
        lambda i, x, img: i < args.steps,
        lambda i, x, img: [
            i + 1, 
            x ** 2 + C, 
            img + tf.cast(~tf.math.is_nan(tf.abs(x)), tf.int32)
        ],
        loop_vars=[tf.constant(0), X, tf.constant(z, dtype=tf.int32)])

with tf.compat.v1.Session() as sess:
    tf.global_variables_initializer()
    img = sess.run(Y, feed_dict={X: z})

plt.imshow(np.sqrt(np.absolute(img)))
plt.show()

