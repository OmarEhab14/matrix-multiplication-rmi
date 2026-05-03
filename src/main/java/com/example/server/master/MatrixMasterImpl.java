package com.example.server.master;

import com.example.common.MatrixMaster;
import com.example.common.MatrixWorker;
import com.example.common.PartialResult;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MatrixMasterImpl extends UnicastRemoteObject implements MatrixMaster {
    private final MatrixWorker worker1;
    private final MatrixWorker worker2;

    public MatrixMasterImpl() throws Exception {
        super();
        worker1 = connect("localhost", 1099);
        worker2 = connect("localhost", 1100);
    }

    @Override
    public int[][] multiplyMatrices(int[][] A, int[][] B) throws RemoteException {
        int mid = A.length / 2;

        int[][] part1 = slice(A, 0, mid);
        int[][] part2 = slice(A, mid, A.length);

        PartialResult r1 = worker1.multiplyRows(part1, B, 0);
        PartialResult r2 = worker2.multiplyRows(part2, B, mid);

        int[][] finalResult = new int[A.length][B[0].length];

        merge(finalResult, r1);
        merge(finalResult, r2);

        return finalResult;
    }

    private MatrixWorker connect(String ip, int port) throws Exception {
        Registry registry = LocateRegistry.getRegistry(ip, port);
        return (MatrixWorker) registry.lookup("MatrixWorker");
    }

    private int[][] slice(int[][] matrix, int from, int to) {
        int[][] res = new int[to - from][matrix[0].length];
        for (int i = from; i < to; i++) {
            res[i - from] = matrix[i].clone();
        }
        return res;
    }

    private void merge(int[][] finalMatrix, PartialResult partial) {
        int start = partial.getStartRow();
        int[][] rows = partial.getRows();

        for (int i = 0; i < rows.length; i++) {
            finalMatrix[start + i] = rows[i];
        }
    }
}
