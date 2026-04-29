package com.example.client;

import com.example.common.MatrixWorker;
import com.example.common.PartialResult;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void main(String[] args) {
        try {
            MatrixWorker worker1 = connect("100.102.127.105", 1099);
            MatrixWorker worker2 = connect("100.64.193.5", 1100);

            int[][] A = {
                    {1, 2},
                    {3, 4},
                    {5, 6},
                    {7, 8}
            };

            int[][] B = {
                    {9, 10},
                    {11, 12}
            };

            int mid = A.length / 2;
            int[][] part1 = sliceRows(A, 0, mid);
            int[][] part2 = sliceRows(A, mid, A.length);

            PartialResult r1 = worker1.multiplyRows(part1, B, 0);
            PartialResult r2 = worker2.multiplyRows(part2, B, mid);


            int[][] finalResult = new int[A.length][B[0].length];

            merge(finalResult, r1);
            merge(finalResult, r2);

            printMatrix(finalResult);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static MatrixWorker connect(String serverIp, int serverPort) throws Exception {
        Registry registry = LocateRegistry.getRegistry(serverIp, serverPort);
        return (MatrixWorker) registry.lookup("MatrixWorker");
    }

    static int[][] sliceRows(int[][] matrix, int from, int to) {
        int[][] result = new int[to - from][matrix[0].length];
        for (int i = from; i < to; i++) {
            result[i - from] = matrix[i].clone();
        }
        return result;
    }

    static void merge(int[][] finalMatrix, PartialResult partial) {
        int start = partial.getStartRow();
        int[][] rows = partial.getRows();

        for (int i = 0; i < rows.length; i++) {
            finalMatrix[start + i] = rows[i];
        }
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
