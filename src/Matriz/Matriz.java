package Matriz;

import java.util.ArrayList;

public class Matriz {
    ArrayList<String> matrizFinal = new ArrayList<>();

    public Matriz() { }

    public Matriz(ArrayList<String> matrizFinal){
        this.matrizFinal = matrizFinal;
    }

    public ArrayList<String> getMatrizFinal() {
        return matrizFinal;
    }

    public void addMatriz(String conteudo){
        matrizFinal.add(conteudo);
    }

    public String getPosicaoMatriz(int posicao ) {
        return matrizFinal.get(posicao);
    }
}
