import Arquivo.Arquivo;
import Interface.Interface;
import MaquinaVirtual.MaquinaVirtual;

public class Main {
    public static void main (String[] args) {
        System.out.println("Maquina Virtual");

        MaquinaVirtual inicioMaquina = new MaquinaVirtual();
        inicioMaquina.start();
    }
}
