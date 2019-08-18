import java.util.concurrent.*;
import java.util.*;

public class Fibonacci { 
    public static class FibonacciRecursiveAction extends RecursiveTask<Long> {
        private final int index;

        public FibonacciRecursiveAction(int index) { 
            this.index = index;
        }

        @Override
        protected Long compute() {
            if (index <= 1) {
                return Long.valueOf(index);
            }
            return ForkJoinTask.invokeAll(Arrays.asList(
                    new FibonacciRecursiveAction(index - 1),
                    new FibonacciRecursiveAction(index - 2)
                )).stream().mapToLong(ForkJoinTask::join).sum();
        }
    }

    public static void main(String... args) {
        RecursiveTask<Long> task = new FibonacciRecursiveAction(Integer.parseInt(args[0]));
        ForkJoinPool fp = ForkJoinPool.commonPool();
        System.out.println(fp.invoke(task));
    }
}
