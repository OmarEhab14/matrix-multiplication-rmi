package com.example.server;

import com.example.common.MatrixWorker;
import com.example.common.PartialResult;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixWorkerImpl extends UnicastRemoteObject implements MatrixWorker {

    public MatrixWorkerImpl() throws RemoteException {}

    @Override
    public PartialResult multiplyRows(int[][] partA, int[][] matrixB, int startRow) throws RemoteException {
        int rows = partA.length;
        int cols = matrixB[0].length;
        int common = matrixB.length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += partA[i][k] * matrixB[k][j];
                }
            }
        }

        return new PartialResult(startRow, result);
    }
}
