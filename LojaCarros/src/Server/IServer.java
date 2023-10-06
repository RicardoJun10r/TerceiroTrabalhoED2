package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.ResponseDTO;
import util.Huffman.HuffTree;

public interface IServer extends Remote {

    void adicionar(String veiculo, HuffTree huffTree) throws RemoteException;
    ResponseDTO remover(String renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO listar(Integer posicao) throws RemoteException;
    ResponseDTO buscarMF(String renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO buscarTR(String renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO buscarCF(String renavam, HuffTree huffTree) throws RemoteException;
    void atualizar(String novo, String renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO quantidadeDeCarros() throws RemoteException;
    ResponseDTO fatorDeCarga() throws RemoteException;
    
}