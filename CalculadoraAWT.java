package br.awt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraAWT extends JFrame implements ActionListener {
    private JTextField Resultado;
    private double primeiroNumero = 0;
    private String operacao = "";
    private boolean novaEntrada = true;

    public CalculadoraAWT() {
        // Configuração da janela
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        // Campo de texto no topo
        Resultado = new JTextField();
        Resultado.setEditable(false);
        Resultado.setHorizontalAlignment(JTextField.RIGHT);
        Resultado.setFont(new Font("Arial", Font.BOLD, 24));
        add(Resultado, BorderLayout.NORTH);

        // Painel para botões
        JPanel AreaCalculo = new JPanel();
        AreaCalculo.setLayout(new GridLayout(4, 4, 5, 5));

        // Botões de números e operações
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.addActionListener(this);
            AreaCalculo.add(botao);
        }

        add(AreaCalculo, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9]")) { // números
            if (novaEntrada) {
                Resultado.setText(comando);
                novaEntrada = false;
            } else {
                Resultado.setText(Resultado.getText() + comando);
            }
        } else if (comando.matches("[+\\-*/]")) { // operações
            primeiroNumero = Double.parseDouble(Resultado.getText());
            operacao = comando;
            novaEntrada = true;
        } else if (comando.equals("=")) {
            try {
                double segundoNumero = Double.parseDouble(Resultado.getText());
                double resultado = 0;

                switch (operacao) {
                    case "+": resultado = primeiroNumero + segundoNumero; break;
                    case "-": resultado = primeiroNumero - segundoNumero; break;
                    case "*": resultado = primeiroNumero * segundoNumero; break;
                    case "/":
                        if (segundoNumero == 0) {
                            Resultado.setText("Erro");
                            return;
                        }
                        resultado = primeiroNumero / segundoNumero;
                        break;
                }

                Resultado.setText(String.valueOf(resultado));
                novaEntrada = true;
            } catch (Exception ex) {
                Resultado.setText("Erro");
            }
        } else if (comando.equals("C")) {
            Resultado.setText("");
            primeiroNumero = 0;
            operacao = "";
            novaEntrada = true;
        }
    }

    public static void main(String[] args) {
        new CalculadoraAWT();
    }
}
