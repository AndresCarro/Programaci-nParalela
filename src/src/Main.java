import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    static int SIZE = 1024;
    static int SEED = 6834723;
    static int RUNS = 20;
    static int NUM_THREADS = 2;

    public static void main(String[] args) {
        for (int j = 0; j < 7; j++) {
            double[] executionTimes = new double[RUNS];

            for (int i = 0; i < RUNS; i++) {
                double startTime = System.currentTimeMillis();
                forkJoinImplementation();
                double endTime = System.currentTimeMillis();
                executionTimes[i] = endTime - startTime;
            }
            System.out.println("Promedio de ejecución (ms): " + calculateMean(executionTimes));
            System.out.println("Desvío Estándar (ms): " + calculateStandardDeviation(executionTimes));
        }
    }

    public static void sequentialImplementation() {
        int i,j,k;
        double[][] A = new double[SIZE][SIZE];
        double[][] B = new double[SIZE][SIZE];
        double[][] C = new double[SIZE][SIZE];
        Random r = new Random(SEED);

        for (i=0; i<SIZE; i++)
            for (j=0; j<SIZE; j++) {
                C[i][j] = 0;
                A[i][j] = r.nextDouble();
                B[i][j] = r.nextDouble();
            }

        for (i=0; i<SIZE; i++)
            for (j=0; j<SIZE; j++)
                for (k=0; k<SIZE; k=k+1)
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];

        System.out.println("Fin: " + C[0][0]);
    }

    public static void concurrencyApiImplementation() {
        double[][] A = new double[SIZE][SIZE];
        double[][] B = new double[SIZE][SIZE];
        double[][] C = new double[SIZE][SIZE];

        Random r = new Random(SEED);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = r.nextDouble();
                B[i][j] = r.nextDouble();
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            final int row = i;
            Callable<Void> task = () -> {
                for (int j = 0; j < SIZE; j++) {
                    C[row][j] = 0;
                    for (int k = 0; k < SIZE; k++) {
                        C[row][j] += A[row][k] * B[k][j];
                    }
                }
                return null;
            };
            futures.add(executor.submit(task));
        }
        for (Future<Void> future : futures) {
            try {                           
                future.get();
            } catch (Exception ignored) {}
        }
        executor.shutdown();

        System.out.println("Fin: " + C[0][0]);
    }

    public static void forkJoinImplementation() {
        double[][] A = new double[SIZE][SIZE];
        double[][] B = new double[SIZE][SIZE];
        double[][] C = new double[SIZE][SIZE];

        Random r = new Random(SEED);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = r.nextDouble();
                B[i][j] = r.nextDouble();
            }
        }

        ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);
        pool.invoke(new MatrixMultiplyTask(A, B, C, 0, SIZE));
        pool.shutdown();

        System.out.println("Fin: " + C[0][0]);
    }

    public static double calculateMean(double[] numbers) {
        double sum = 0.0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    public static double calculateStandardDeviation(double[] numbers) {
        double mean = calculateMean(numbers);
        double sumOfSquares = 0.0;

        for (double num : numbers) {
            sumOfSquares += Math.pow(num - mean, 2);
        }

        return Math.sqrt(sumOfSquares / numbers.length);
    }

    static class MatrixMultiplyTask extends RecursiveAction {
        private static final int THRESHOLD = 100;
        private double[][] A, B, C;
        private final int startRow, endRow;

        public MatrixMultiplyTask(double[][] A, double[][] B, double[][] C, int startRow, int endRow) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        protected void compute() {
            if (endRow - startRow <= THRESHOLD) {
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        C[i][j] = 0;
                        for (int k = 0; k < SIZE; k++) {
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            } else {
                int mid = (startRow + endRow) / 2;
                MatrixMultiplyTask task1 = new MatrixMultiplyTask(A, B, C, startRow, mid);
                MatrixMultiplyTask task2 = new MatrixMultiplyTask(A, B, C, mid, endRow);
                invokeAll(task1, task2);
            }
        }
    }

}