class Matrix {
    private final int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getCols() {
        return matrix[0].length;
    }
}

class MatrixAdditionThread extends Thread {
    private final Matrix matrix1;
    private final Matrix matrix2;
    private final Matrix result;
    private final int startRow;
    private final int endRow;

    public MatrixAdditionThread(Matrix matrix1, Matrix matrix2, Matrix result, int startRow, int endRow) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < matrix1.getCols(); j++) {
                result.getMatrix()[i][j] = matrix1.getMatrix()[i][j] + matrix2.getMatrix()[i][j];
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] arr1 = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        int[][] arr2 = { {9, 8, 7}, {6, 5, 4}, {3, 2, 1} };

        Matrix matrix1 = new Matrix(arr1);
        Matrix matrix2 = new Matrix(arr2);

        if (matrix1.getRows() != matrix2.getRows() || matrix1.getCols() != matrix2.getCols()) {
            System.out.println("Matrices should have the same dimensions for addition.");
            return;
        }

        int rows = matrix1.getRows();
        int cols = matrix1.getCols();
        Matrix result = new Matrix(new int[rows][cols]);

        int numThreads = 4; // Number of threads
        Thread[] threads = new Thread[numThreads];
        int rowsPerThread = rows / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i == numThreads - 1) ? rows : (i + 1) * rowsPerThread;
            threads[i] = new MatrixAdditionThread(matrix1, matrix2, result, startRow, endRow);
            threads[i].start();
        }

        // Wait for all threads to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the result
        System.out.println("Matrix Addition Result:");
        int[][] resultMatrix = result.getMatrix();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
