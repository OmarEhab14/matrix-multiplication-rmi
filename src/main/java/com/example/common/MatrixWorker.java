package com.example.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatrixWorker extends Remote {
    PartialResult multiplyRows(
            int[][] partA,
            int[][] matrixB,
            int startRow
    ) throws RemoteException;
}
