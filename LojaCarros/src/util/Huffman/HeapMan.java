package util.Huffman;

import java.io.Serializable;

public class HeapMan implements Serializable {

    private Lista<HuffNode> lista;

    public HeapMan(){
        this.lista = new Lista<>();
    }

    public void Add(HuffNode valor){

        if(this.lista.Empty()){
            lista.Add(valor);
        } else {
            lista.Add(valor);
            Subir(this.lista.Size()-1);
        }

    }

    public HuffNode Delete(){
        if(this.lista.Empty()) return null;
        else {
            HuffNode tmp = this.lista.Get(0);
            this.lista.Set(0, this.lista.Get(this.lista.Size()-1));
            this.lista.DeleteTail();
            Descer(0, this.lista.Size());
            return tmp;
        }
    }

    private int Piso(int numero){
        return (int)Math.floor(numero);
    }

    private void Subir(int posicao){
        
        int j = Piso((posicao - 1) / 2);

        if(j >= 0 && (lista.Get(j).getFrequencia() > lista.Get(posicao).getFrequencia())){
            HuffNode aux = lista.Get(j);
            lista.Set(j, lista.Get(posicao));
            lista.Set(posicao, aux);
            Subir(j);
        }

    }

    private void Descer(int posicao, int tamanho){
        
        int j = (posicao * 2) + 1;

        if(j < tamanho){
            if(j < tamanho - 1){
                if(lista.Get(j + 1).getFrequencia() < lista.Get(j).getFrequencia()){
                    j++;
                }

            }
            if(lista.Get(posicao).getFrequencia() > lista.Get(j).getFrequencia()){
                HuffNode aux = lista.Get(j);
                lista.Set(j, lista.Get(posicao));
                lista.Set(posicao, aux);
                Descer(j, tamanho);
            }

        }

    }

}
