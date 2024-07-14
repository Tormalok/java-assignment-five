import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("Choose the type of algorithm:");
            System.out.println("1. Greedy");
            System.out.println("2. Divide and Conquer");
            System.out.println("3. Compare algorithms");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Choose a Greedy algorithm:");
                    System.out.println("1. Travelling Salesman Problem");
                    System.out.println("2. Dijkstra's Algorithm");
                    System.out.println("3. Huffman Coding");
                    System.out.println("0. Exit");

                    int greedyChoice = scanner.nextInt();
                    switch (greedyChoice) {
                        case 1:
                            runTravellingSalesman();
                            break;
                        case 2:
                            runDijkstraAlgorithm();
                            break;
                        case 3:
                            runHuffmanCoding();
                            break;
                        case 0:
                            continueProgram = false;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;
                case 2:
                    System.out.println("Choose a Divide and Conquer algorithm:");
                    System.out.println("1. Quick Sort");
                    System.out.println("2. Merge Sort");
                    System.out.println("3. Strassen's Matrix Multiplication");
                    System.out.println("4. Closest Pair of Points");
                    System.out.println("5. QuickHull");
                    System.out.println("0. Exit");

                    int divideAndConquerChoice = scanner.nextInt();
                    switch (divideAndConquerChoice) {
                        case 1:
                            runQuickSortWithInput();
                            break;
                        case 2:
                            runMergeSortWithInput();
                            break;
                        case 3:
                            runStrassenMatrixMultiplicationWithInput();
                            break;
                        case 4:
                            runClosestPair();
                            break;
                        case 5:
                            runQuickHull();
                            break;
                        case 0:
                            continueProgram = false;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    compareAlgorithms(scanner);
                    break;
                case 0:
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void compareAlgorithms(Scanner scanner) {
        System.out.println("Choose two algorithms to compare:");
        System.out.println("1. Quick Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Strassen's Matrix Multiplication");
        System.out.println("0. Exit");

        int firstChoice = scanner.nextInt();
        if (firstChoice == 0) return;

        int secondChoice = scanner.nextInt();
        if (secondChoice == 0) return;

        if (firstChoice == secondChoice) {
            System.out.println("Please choose two different algorithms.");
            return;
        }

        if ((firstChoice == 1 || firstChoice == 2) && (secondChoice == 1 || secondChoice == 2)) {
            System.out.print("Enter the number of elements: ");
            int n = scanner.nextInt();
            int[] array1 = new int[n];
            int[] array2 = new int[n];
            System.out.println("Enter the elements:");
            for (int i = 0; i < n; i++) {
                array1[i] = scanner.nextInt();
                array2[i] = array1[i];
            }

            switch (firstChoice) {
                case 1:
                    runQuickSort(array1);
                    break;
                case 2:
                    runMergeSort(array1);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            switch (secondChoice) {
                case 1:
                    runQuickSort(array2);
                    break;
                case 2:
                    runMergeSort(array2);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else if ((firstChoice == 3) && (secondChoice == 3)) {
            System.out.print("Enter the size of the matrices (n x n): ");
            int n = scanner.nextInt();
            int[][] A1 = new int[n][n];
            int[][] B1 = new int[n][n];
            int[][] A2 = new int[n][n];
            int[][] B2 = new int[n][n];

            System.out.println("Enter matrix A:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A1[i][j] = scanner.nextInt();
                    A2[i][j] = A1[i][j];
                }
            }
            System.out.println("Enter matrix B:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    B1[i][j] = scanner.nextInt();
                    B2[i][j] = B1[i][j];
                }
            }

            switch (firstChoice) {
                case 3:
                    runStrassenMatrixMultiplication(A1, B1);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            switch (secondChoice) {
                case 3:
                    runStrassenMatrixMultiplication(A2, B2);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid combination of algorithms for comparison.");
        }
    }

    private static void runTravellingSalesman() {
        long startTime = System.currentTimeMillis();
        TravellingSalesman.main(new String[]{});
        long endTime = System.currentTimeMillis();
        System.out.println("Travelling Salesman Problem runtime: " + (endTime - startTime) + " ms");
    }

    private static void runDijkstraAlgorithm() {
        long startTime = System.currentTimeMillis();
        DijkstraAlgorithm.main(new String[]{});
        long endTime = System.currentTimeMillis();
        System.out.println("Dijkstra's Algorithm runtime: " + (endTime - startTime) + " ms");
    }

    private static void runHuffmanCoding() {
        long startTime = System.currentTimeMillis();
        HuffmanTree.main(new String[]{});
        long endTime = System.currentTimeMillis();
        System.out.println("Huffman Coding runtime: " + (endTime - startTime) + " ms");
    }

    private static void runQuickSort(int[] array) {
        QuickSort quickSort = new QuickSort();
        long startTime = System.currentTimeMillis();
        quickSort.sort(array);
        long endTime = System.currentTimeMillis();
        System.out.println("Quick Sort runtime: " + (endTime - startTime) + " ms");
        System.out.println("Sorted array:");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void runMergeSort(int[] array) {
        MergeSort mergeSort = new MergeSort();
        long startTime = System.currentTimeMillis();
        mergeSort.sort(array);
        long endTime = System.currentTimeMillis();
        System.out.println("Merge Sort runtime: " + (endTime - startTime) + " ms");
        System.out.println("Sorted array:");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void runStrassenMatrixMultiplication(int[][] A, int[][] B) {
        long startTime = System.currentTimeMillis();
        int[][] C = StrassenMatrixMultiplication.strassenMultiply(A, B);
        long endTime = System.currentTimeMillis();
        System.out.println("Strassen's Matrix Multiplication runtime: " + (endTime - startTime) + " ms");
        System.out.println("Resulting matrix C:");
        for (int[] row : C) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static void runStrassenMatrixMultiplicationWithInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the matrices (n x n): ");
        int n = scanner.nextInt();
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        System.out.println("Enter matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = scanner.nextInt();
            }
        }
        runStrassenMatrixMultiplication(A, B);
    }

    private static void runClosestPair() {
        long startTime = System.currentTimeMillis();
        ClosestPair.main(new String[]{});
        long endTime = System.currentTimeMillis();
        System.out.println("Closest Pair of Points runtime: " + (endTime - startTime) + " ms");
    }

    private static void runQuickHull() {
        long startTime = System.currentTimeMillis();
        QuickHull.main(new String[]{});
        long endTime = System.currentTimeMillis();
        System.out.println("QuickHull runtime: " + (endTime - startTime) + " ms");
    }

    private static void runQuickSortWithInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        runQuickSort(array);
    }

    private static void runMergeSortWithInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        runMergeSort(array);
    }
}