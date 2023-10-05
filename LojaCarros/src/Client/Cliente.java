package Client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Scanner;

import Server.IServer;
import util.Huffman.HuffTree;

public class Cliente {
    
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException, IOException, NotBoundException {
        String objName = "rmi://localhost:1099/lojaCarros";
        IServer veiculos = (IServer) Naming.lookup(objName);
        int opcao = 1;

        carregarCarros(veiculos);

        try {
            do {
                menu();
                opcao = scan.nextInt();
                processOption(opcao, veiculos);
            } while (opcao != 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void carregarCarros(IServer veiculoService) throws RemoteException{

        String carro = "";

        // HuffTree huffTree = veiculoService.instanciar();

        for(int i = 0; i < 1; i++){

            carro += String.valueOf(i) + "0015468" + ";";
            carro += "Kwid" + i + ";";
            carro += "Renault" + i + ";";
            carro += "QRT12" + i + ";";
            carro += LocalDate.now().toString() + ";";
            carro += "Roberto" + i + ";";
            carro += "041646" + i;

            System.out.println(carro);

            String compress = veiculoService.instanciar().Compress(carro);

            System.out.println(compress);

            veiculoService.adicionar(compress);

            carro = "";
        }

    }

    static void menu(){
        System.out.print("\n[0] - sair\n[1] - adicionar\n[2] - remover\n[3] - listar\n[4] - buscar ( usando MOVER PARA FRENTE)\n[5] - buscar ( usando TRANSPOSIÇÃO)\n[6] - buscar ( usando CONTADOR DE FREQUẼNCIA)\n[7] - atualizar\n[8] - quantidade de carros\n[9] - fator de carga\nOpção: [_]\b\b");
    }

    static void processOption(int opcao , IServer veiculos) throws RemoteException{
        try {

            HuffTree huffTree = veiculos.instanciar();

            switch(opcao){
            case 0:
            {
                System.out.println("Tchau!");
                break;
            }
            case 1:
            {
                String renavam, nome, modelo, placa, dataString, condutor, cpf;
                System.out.println("Renavam:");
                renavam = scan.next();
                scan.nextLine();
                System.out.println("Nome do carro:");
                nome = scan.next();
                System.out.println("Modelo do carro:");
                modelo = scan.next();
                System.out.println("Placa do carro:");
                placa = scan.next();
                scan.nextLine();
                System.out.println("Data Fabricação do carro( ex: ANO-MES-DIA ): ");
                LocalDate data = LocalDate.parse(scan.next());
                dataString = data.toString();
                scan.nextLine();
                System.out.println("Nome do condutor:");
                condutor = scan.nextLine();
                System.out.println("CPF do condutor:");
                cpf = scan.next();

                String carro = renavam + ";" + nome + ";" + modelo + ";" + placa + ";" + dataString + ";" + condutor + ";" + cpf;

                veiculos.adicionar(huffTree.Compress(carro));
                break;
            }
            case 2:
            {
                String renavam;
                System.out.println("Renavam:");
                renavam = scan.next();
                String res = huffTree.Decompress(veiculos.remover( huffTree.Compress(renavam) ));
                System.out.println( res );
                break;
            }
            case 3:
            {
                System.out.println("\nListando");
                String res = huffTree.Decompress(veiculos.listar());
                System.out.println( res );
                break;
            }
            case 4:
            {
                String renavam;
                System.out.println("Renavam:");
                renavam = scan.next();
                String veiculo = huffTree.Decompress( veiculos.buscarMF( huffTree.Compress(renavam)) );
                System.out.println(veiculo);
                break;
            }
            case 5:
            {
                String renavam;
                System.out.println("Renavam:");
                renavam = scan.next();
                String veiculo = huffTree.Decompress( veiculos.buscarTR( huffTree.Compress(renavam)) );
                System.out.println(veiculo);
                break;
            }
            case 6:
            {
                String renavam;
                System.out.println("Renavam:");
                renavam = scan.next();
                String veiculo = huffTree.Decompress( veiculos.buscarCF( huffTree.Compress(renavam)) );
                System.out.println(veiculo);
                break;
            }
            case 7:
            {
                String renavam, nome, modelo, placa, dataString, condutor, cpf;
                System.out.println("Renavam do veículo a atualizar:");
                renavam = scan.next();
                System.out.println("Nome do carro ou '*' (vazio):");
                nome = scan.next();
                System.out.println("Modelo do carro ou '*' (vazio):");
                modelo = scan.next();
                System.out.println("Placa do carro ou '*' (vazio):");
                placa = scan.next();
                scan.nextLine();
                System.out.println("Data Fabricação do carro: ");
                LocalDate data = LocalDate.parse(scan.nextLine());
                dataString = data.toString();
                scan.nextLine();
                System.out.println("Nome do condutor ou '*' (vazio):");
                condutor = scan.nextLine();
                System.out.println("CPF do condutor ou '*' (vazio):");
                cpf = scan.next();

                String carro = renavam + ";" + nome + ";" + modelo + ";" + placa + ";" + dataString + ";" + condutor + ";" + cpf;

                veiculos.atualizar(huffTree.Compress(carro), huffTree.Compress(renavam));
                break;
            }
            case 8:
            {
                String res = huffTree.Decompress(veiculos.quantidadeDeCarros());
                System.out.println("Existem: " + res + " carros cadastrados");
                break;
            }
            case 9:
            {
                String res = huffTree.Decompress(veiculos.fatorDeCarga());
                System.out.println("Fator de carga: " + res + "%");
                break;
            }
            default:
                System.out.println("Opção inexistente!!!");
                break;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
