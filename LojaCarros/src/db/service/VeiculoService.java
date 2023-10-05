package db.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;

import Server.IServer;
import db.model.Condutor;
import db.model.Veiculo;
import util.HashTable.Table;
import util.Huffman.HuffTree;

public class VeiculoService extends UnicastRemoteObject implements IServer {

    private Table<Veiculo, Integer> tabela;

    private HuffTree huffTree;

    public VeiculoService() throws RemoteException {
        this.tabela = new Table<>();
        this.huffTree = HuffTree.Instanciar();
    }

    @Override
    public void adicionar(String veiculo) throws RemoteException {
        String decompess = huffTree.Decompress(veiculo);
        String[] resposta = decompess.split(";");
        LocalDate localDate = LocalDate.parse(resposta[4]);
        Veiculo novo = new Veiculo(resposta[0], resposta[1], resposta[2], resposta[3], localDate, new Condutor(resposta[5], resposta[6]));
        this.tabela.Adicionar(novo, Integer.parseInt(novo.getRenavam()));
    }

    @Override
    public String remover(String renavam) throws RemoteException {
        String res = huffTree.Decompress(renavam);
        this.tabela.Remover(Integer.parseInt(res));
        return huffTree.Compress("Removido veículo [ " + res + " ]");
    }

    @Override
    public String listar() throws RemoteException {
        return huffTree.Compress(this.tabela.Print());
    }

    @Override
    public void atualizar(String novo, String renavam) throws RemoteException {
        String decompess = huffTree.Decompress(novo);
        String[] resposta = decompess.split(";");
        LocalDate localDate = LocalDate.parse(resposta[4]);
        Veiculo novoVeiculo = new Veiculo(resposta[0], resposta[1], resposta[2], resposta[3], localDate, new Condutor(resposta[5], resposta[6]));
        Veiculo velhoVeiculo = this.tabela.BuscarMF(Integer.parseInt(huffTree.Decompress(renavam))).getValor();
        if(attCampos(novoVeiculo, velhoVeiculo)){
            this.tabela.Atualizar(velhoVeiculo, Integer.parseInt(velhoVeiculo.getRenavam()));
        }
    }

    @Override
    public String quantidadeDeCarros() throws RemoteException {
        return huffTree.Compress(String.valueOf( this.tabela.Tamanho() ));
    }

    @Override
    public String fatorDeCarga() throws RemoteException {
        return huffTree.Compress(String.valueOf( this.tabela.FatorDeCarga() ));
    }

    @Override
    public String buscarMF(String renavam) throws RemoteException {
        return huffTree.Compress(this.tabela.BuscarMF(Integer.parseInt(huffTree.Decompress(renavam))).toString());
    }

    @Override
    public String buscarTR(String renavam) throws RemoteException {
        return huffTree.Compress(this.tabela.BuscarTR(Integer.parseInt(huffTree.Decompress(renavam))).toString());

    }

    @Override
    public String buscarCF(String renavam) throws RemoteException {
        return huffTree.Compress(this.tabela.BuscarCF(Integer.parseInt(huffTree.Decompress(renavam))).toString());
    }

    private Boolean attCampos(Veiculo novo, Veiculo velho){

        boolean hasAtt = false;

        if(novo.getNome() != null && !novo.getNome().isEmpty() && !novo.getNome().equalsIgnoreCase("*")){
            velho.setNome(novo.getNome());
            hasAtt = true;
        }
        if(novo.getModelo() != null && !novo.getModelo().isEmpty() && !novo.getModelo().equalsIgnoreCase("*")){
            velho.setModelo(novo.getModelo());
            hasAtt = true;
        }
        if(novo.getPlaca() != null && !novo.getPlaca().isEmpty() && !novo.getPlaca().equalsIgnoreCase("*")){
            velho.setPlaca(novo.getPlaca());
            hasAtt = true;
        }
        if(novo.getDataFabricacao() != null && !novo.getDataFabricacao().equals("*")){
            velho.setDataFabricacao(novo.getDataFabricacao());
            hasAtt = true;
        }
        if(novo.getCondutor().getNome() != null && !novo.getCondutor().getNome().isEmpty() && !novo.getCondutor().getNome().equalsIgnoreCase("*")){
            velho.getCondutor().setNome(novo.getCondutor().getNome());
            hasAtt = true;
        }
        if(novo.getCondutor().getCpf() != null && !novo.getCondutor().getCpf().isEmpty() && !novo.getCondutor().getCpf().equalsIgnoreCase("*")){
            velho.getCondutor().setCpf(novo.getCondutor().getCpf());
            hasAtt = true;
        }

        return hasAtt;
        
    }
    
}
