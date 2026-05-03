package com.example.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatrixMaster extends Remote {
    int[][] multiplyMatrices(int[][] A, int[][] B) throws RemoteException;
}
