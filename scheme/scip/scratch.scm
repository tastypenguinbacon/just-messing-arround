(define (power a acc n)
	(cond ((= n 0) acc)
		((even? n) (power (* a a) acc (/ n 2)))
		(else (power a (* a acc) (- n 1)))))

(define (invert col acc)
	(if (null? col)
		acc
		(invert (rest col)
			(cons (first col) acc)))) 

(define (partial fun arg1 arg2)
	(lambda (x) (fun arg1 arg2 x)))

(define (long-list n acc)
	(if (< n 0)
		acc
		(long-list (- n 1) 
			(cons n acc))))
		

(println (invert (map (partial power 2 1) (long-list 100000 `())) `()))
