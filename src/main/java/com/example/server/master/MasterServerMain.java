package com.example.server.master;

import com.example.common.MatrixMaster;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MasterServerMain {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java MasterServerMain <port>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        MatrixMaster master = new MatrixMasterImpl();
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("MatrixMaster", master);
        System.out.println("Master server is running on port " + port);
    }
}
