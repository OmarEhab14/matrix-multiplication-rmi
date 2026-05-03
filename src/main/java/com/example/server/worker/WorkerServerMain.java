package com.example.server.worker;

import com.example.common.MatrixWorker;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WorkerServerMain {
    public static void main(String[] args) throws RemoteException {
        if (args.length < 1) {
            System.err.println("Usage: java ServerMain <port>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        MatrixWorker worker = new MatrixWorkerImpl();
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("MatrixWorker", worker);
        System.out.println("Worker Server is running on port " + port);
    }
}
