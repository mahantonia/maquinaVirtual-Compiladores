import Arquivo.Arquivo;
import Interface.Interface;
import MaquinaVirtual.MaquinaVirtual;

public class Main {
    public static void main (String[] args) {
        System.out.println("Ola");
//
//        Arquivo ola = new Arquivo();
//
//        ola.selecionaArquivo();

//        Interface aa = new Interface();
//
//        aa.start();

        MaquinaVirtual inicioMaquina = new MaquinaVirtual();

        inicioMaquina.start();
    }
}
