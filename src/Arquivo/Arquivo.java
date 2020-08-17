package Arquivo;

import Instrucoes.Instrucoes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Arquivo {
    public Arquivo(){ }

    Instrucoes instrucao = new Instrucoes();
    ArrayList<String> matrizInstrucoes = new ArrayList<>();
    ArrayList<String> matrizAuxiliar = new ArrayList<>();
    ArrayList<String> matrizFinal = new ArrayList<>();

    public void selecionaArquivo() throws Exception {
        String caminhoArquivo = "";
        Component parent = null;

        JFileChooser selecionarArquivo = new JFileChooser();
        File arquivoSelecionado = null;

        selecionarArquivo.setFileFilter(null);
        int returnVal = selecionarArquivo.showOpenDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Voce escolheu o arquivo: "+ selecionarArquivo.getSelectedFile().getName());
            caminhoArquivo = selecionarArquivo.getSelectedFile().getAbsolutePath();

            if(caminhoArquivo != null){
                System.out.println("Arquivo Selecionado com sucesso");
                leArquivo(caminhoArquivo);
            }
        }
    }

    private void leArquivo(String caminho) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while (br.ready()) {
            linha = br.readLine();
            matrizInstrucoes.add(linha);
        }
        br.close();
        System.out.println(matrizInstrucoes);
        bla(matrizInstrucoes);
        separaConteudoLinha();
    }

    private void bla(ArrayList<String> matrizInstrucoes) {
        String teste[];
        for(int i = 0; i < matrizInstrucoes.size(); i++){
            teste = matrizInstrucoes.get(i).split(" ");
            if(teste.length == 2){
                if(teste[1].equals("NULL")){
                    String posicaoNomeLinha = teste[0] + " " + teste[1] + " " + Integer.toString(i);
                    matrizAuxiliar.add(posicaoNomeLinha);
                }
            }
        }
        System.out.println(matrizAuxiliar);
        geraMatrizFinal();
    }

    private void geraMatrizFinal() {
        String palavra[];

        for(int i = 0; i < matrizInstrucoes.size(); i++){
            palavra = matrizInstrucoes.get(i).split(" ");
            switch (palavra.length) {
                case 1:
                    matrizFinal.add(palavra[0]);
                    break;
                case 2:
                    if (palavra[1].equals("NULL")) {
                        setPosicaoLinha(palavra[0], palavra[1]);
                    } else {
                        if((palavra[0].equals("CALL")) || (palavra[0].equals("JMP")) || (palavra[0].equals("JMPF"))){
                            setPosicaoLinha(palavra[1], palavra[0]);
                        }else{
                            String instrucao = palavra[0] + " " + palavra[1];
                            matrizFinal.add(instrucao);
                        }
                    }
                    break;
                case 3:
                    String instrucao = palavra[0] + " " + palavra[1] + " " + palavra[2];
                    matrizFinal.add(instrucao);
                    break;
            }
        }
    }

    private void setPosicaoLinha(String palavraPosicao, String palavra) {
        String posicao[], teste;

        for(int i = 0; i < matrizAuxiliar.size(); i++){
            posicao = matrizAuxiliar.get(i).split(" ");
            if(palavraPosicao.equals(posicao[0])){
                if(palavra.equals("NULL")){
                    teste = posicao[2] + " " + palavra;
                    matrizFinal.add(teste);
                }else{
                    teste =  palavra + " " + posicao[2];
                    matrizFinal.add(teste);
                }


            }
        }
    }

    private void separaConteudoLinha() {
        String conteudoLinhaSeparado[] = new String[4];

        System.out.println(matrizFinal);
        while (instrucao.getI() != -1) {
            conteudoLinhaSeparado = matrizFinal.get(instrucao.getI()).split(" ");

            switch(conteudoLinhaSeparado.length){
                case 1:
                    instrucao.intrucoesTipo1(conteudoLinhaSeparado[0]);
                    System.out.println(conteudoLinhaSeparado[0]);
                    break;
                case 2:
                    if(conteudoLinhaSeparado[1].equals("NULL")){
                        instrucao.intrucoesTipo1(conteudoLinhaSeparado[1]);
                        System.out.println(conteudoLinhaSeparado[1]);
                    }else{
                        instrucao.intrucoesTipo2(conteudoLinhaSeparado[0], Integer.parseInt(conteudoLinhaSeparado[1]));
                        System.out.println(conteudoLinhaSeparado[0]);
                    }
                    break;
                case 3:
                    instrucao.intrucoesTipo3(conteudoLinhaSeparado[0], Integer.parseInt(conteudoLinhaSeparado[1]), Integer.parseInt(conteudoLinhaSeparado[2]));
                    System.out.println(conteudoLinhaSeparado[0]);
                    break;
            }
        }
    }
}
