package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    void adicionar(String veiculo) throws RemoteException;
    String remover(String renavam) throws RemoteException;
    String listar() throws RemoteException;
    String buscarMF(String renavam) throws RemoteException;
    String buscarTR(String renavam) throws RemoteException;
    String buscarCF(String renavam) throws RemoteException;
    void atualizar(String novo, String renavam) throws RemoteException;
    String quantidadeDeCarros() throws RemoteException;
    String fatorDeCarga() throws RemoteException;
    
}