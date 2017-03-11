queue_test: queue_test.o queue.o
	gcc -o queue_test queue_test.o queue.o
	make clean
	./queue_test
	rm queue_test

queue_test.o: queue/queue_test.c test/test.h test/colors.h
	gcc -Wall -Wextra -Wpedantic -c queue/queue_test.c

queue.o: queue/queue.c queue/queue.h
	gcc -Wall -Wextra -Wpedantic -c queue/queue.c

clean:
		rm *.o
