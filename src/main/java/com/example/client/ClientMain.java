package com.example.client;

import com.example.common.MatrixMaster;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2000);

            MatrixMaster master = (MatrixMaster) registry.lookup("MatrixMaster");

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

            int[][] result = master.multiplyMatrices(A, B);

            printMatrix(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
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
