package Interface;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;

public class Interface extends JFrame{
    private JPanel painelInstrucao;
    private JPanel painelDados;
    private JPanel painelSaida;
    private JPanel painelEntrada;
//    private JButton botaoExecutar;


    public void start(){
        iniciarTela();
    }

    public void iniciarTela(){
        this.setVisible(true);
        this.setSize(1200,800);
        this.setTitle("Maquina Virtual");
        this.setLayout(null);
        this.setResizable(false);

        painelInstrucao = new JPanel();
        painelInstrucao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Instrucoes"));
        painelInstrucao.setBounds(10,10,500,740);
        this.add(painelInstrucao);

        painelDados = new JPanel();
        painelDados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados"));
        painelDados.setBounds(540,10,300,630);
        this.add(painelDados);

        painelSaida = new JPanel();
        painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Saida"));
        painelSaida.setBounds(870,10,300,300);
        this.add(painelSaida);

        painelEntrada = new JPanel();
        painelEntrada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Entrada de Dados"));
        painelEntrada.setBounds(870,340,300,300);
        this.add(painelEntrada);

//        botaoExecutar = new JButton("Executar");
//        botaoExecutar.setBounds(540, 500, 150,50);
//        this.add(botaoExecutar);


        //Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
//        pack();
//        setLocationRelativeTo(null);

    }
}
