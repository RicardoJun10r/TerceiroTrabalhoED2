package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import db.service.VeiculoService;

public class Servidor {
    
    public static void main(String[] args) {
        try {
            IServer veiculoService = new VeiculoService();
            String objName = "rmi://localhost/lojaCarros";
			
            System.out.println("Inicializando...");
			LocateRegistry.createRegistry(1099);
            Naming.rebind(objName, veiculoService);
            System.out.println("Aguardando requisição!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
