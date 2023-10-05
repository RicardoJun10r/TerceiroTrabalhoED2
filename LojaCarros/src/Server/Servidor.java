package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import db.service.VeiculoService;
import util.Huffman.HuffTree;

public class Servidor {
    
    public static void main(String[] args) {
        try {
            IServer veiculoService = new VeiculoService();
            String objName = "rmi://localhost:1099/lojaCarros";
			HuffTree huffTree = HuffTree.Instanciar();
            System.out.println("Inicializando...");
			LocateRegistry.createRegistry(1099);
            Naming.rebind(objName, veiculoService);
            System.out.println("Aguardando requisição!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
