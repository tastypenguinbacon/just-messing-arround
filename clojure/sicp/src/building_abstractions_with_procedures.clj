(ns building-abstractions-with-procedures
  (:use clojure.test))

(defn fib-iter [a b p q count]
  (cond
    (= count 0) b
    (even? count) (fib-iter (+ (* a a) (* b b))
                            (- (* 2 a b) (* b b))
                            (- (* 2 a b) (* b b))
                            (+ (* b b 2) (* 2 a b -1) (* a a))
                            (/ count 2))
    :else (fib-iter (+ (* b q) (* a q) (* a p))
                    (+ (* b p) (* a q))
                    p
                    q
                    (- count 1))))

(defn square-root-by-newton [x]

  )

(is (= (* (+ 2 (* 4 6))
          (+ 3 5 7)) 3900))