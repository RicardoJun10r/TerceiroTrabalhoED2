package util.Huffman;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class HuffTree implements Serializable {

    private Map<Character, Integer> map;

    private HuffNode raiz;

    private Character[] texto;

    private HeapMan heapMan;

    private Integer contador;

    public HuffTree(){
        this.map = new HashMap<>();
        this.heapMan = new HeapMan();
        this.raiz = null;
        this.contador = 0;
    }

    public void Clear(){
        ClearTree(this.raiz);
        this.map.clear();
        this.texto = null;
    }

    private void ClearTree(HuffNode no){
        if(no != null){
            ClearTree(no.getEsq());
            ClearTree(no.getDir());
            no = null;
        }
    }

    private void Freq(){

        int freq = 1;
        for(int i = 0; i < texto.length; i++){
            if(this.map.containsKey(texto[i])){
                this.map.replace(texto[i], this.map.get(texto[i]), (this.map.get(texto[i]) + 1));
            } else {
                this.map.put(texto[i], freq);
            }
        }

    }

    private void BuildHeap(){
        Set<Entry<Character, Integer>> set = this.map.entrySet();

        for (Entry<Character,Integer> entry : set) {
            this.heapMan.Add(new HuffNode(entry.getKey(), entry.getValue()));
            contador++;
        }
    }

    public Character[] Compress(String texto){

        this.texto = StringToCharacter(texto);

        BuildTree();

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < texto.length(); i++) {
            Search(this.texto[i], stringBuffer);
        }

        return StringToCharacter(stringBuffer.toString());

    }

    private Character[] StringToCharacter(String text){
        Character[] characters = new Character[text.length()];

        for (int i = 0; i < text.length(); i++) {
            characters[i] = text.charAt(i);
        }
        return characters;
    }

    public String Decompress(Character[] code){
        StringBuffer stringBuffer = new StringBuffer();
        Integer contador = 0;
        Decompress(this.raiz, code, contador, stringBuffer);
        return stringBuffer.toString();
    }

    private void Decompress(HuffNode no, Character[] texto, Integer contador, StringBuffer stringBuffer){

        if(no == null) return;
        
        if(isNode(no)){
            stringBuffer.append(no.getCaractere());
            Decompress(this.raiz, texto, contador, stringBuffer);
        }

        if(contador == (texto.length)) return;

        if(String.valueOf(texto[contador]).equals("0")){
            contador++;
            Decompress(no.getEsq(), texto, contador, stringBuffer);
        } else if(String.valueOf(texto[contador]).equals("1")){
            contador++;
            Decompress(no.getDir(), texto, contador, stringBuffer);
        }
    }

    private void Search(Character caractere, StringBuffer stringBuffer){
        Search(raiz, caractere, stringBuffer);
    }

    private void Search(HuffNode no, Character caractere, StringBuffer stringBuffer){

        if(isNode(no)) return;

        if(no.getEsq().getCaractere() == caractere){
            stringBuffer.append("0");
            Search(no.getEsq(), caractere, stringBuffer);
        } else {
            stringBuffer.append("1");
            Search(no.getDir(), caractere, stringBuffer);
        }

    }

    private void BuildTree(){

        Freq();

        BuildHeap();
        
        for (int i = 0; i < contador; i++) {
            Add(this.heapMan.Delete());
        }

    }

    private void Add(HuffNode valor){
        if(Empty()) {
            this.raiz = valor;
        } else {
            this.raiz = Add(raiz, valor);
        }
    }

    private HuffNode Add(HuffNode no, HuffNode novo){

        Integer cont = 0;

        cont += no.getFrequencia() + novo.getFrequencia();
        
        HuffNode novaRaiz = new HuffNode('*', cont);

        novaRaiz.setDir(no);
        novaRaiz.setEsq(novo);

        return novaRaiz;

    }

    public void PrintTree(){
        PrintTree(this.raiz);
    }

    private void PrintTree(HuffNode no){
        if(no != null){
            System.out.println("Caractere = " + no.getCaractere() + " Frequencia = " + no.getFrequencia());
            PrintTree(no.getEsq());
            PrintTree(no.getDir());
        }
    }

    private Boolean isNode(HuffNode no){
        if(no.getEsq() == null && no.getDir() == null) return true;
        else return false;
    }

    private Boolean Empty(){
        if(this.raiz == null) return true;
        else return false;
    }

    public void Print(){
        System.out.println(this.map.toString());
    }
}
