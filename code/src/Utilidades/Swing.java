package Utilidades;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Swing {

    // Main temporário, somente usado para testar a implementação
    public static void main(String[] args) {
        JFrame swingFrame = new JFrame("Casino");
        swingFrame.setSize(1280, 720);
        swingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel swingPanel = new JPanel();
        swingPanel.setSize(300, 300);
        swingPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel swingLabel = new JLabel("Qual jogo você deseja jogar?");
        swingPanel.add(swingLabel, gbc);

        gbc.insets = new Insets(15, 0, 10, 0);

        // Adicionando e personalizando botao1 com borda arredondada
        gbc.gridy = 1;
        JButton botao1 = new JButton("Roleta");
        botao1.setFont(new Font("Arial", Font.BOLD, 16));
        botao1.setPreferredSize(new Dimension(100, 30));
        botao1.setFocusPainted(false);
        swingPanel.add(botao1, gbc);

        gbc.insets = new Insets(5, 0, 10, 0);

        gbc.gridy = 2;
        JButton botao2 = new JButton("Bingo");
        botao2.setFont(new Font("Arial", Font.BOLD, 16));
        botao2.setPreferredSize(new Dimension(100, 30));
        botao2.setFocusPainted(false);
        swingPanel.add(botao2, gbc);

        gbc.insets = new Insets(5, 0, 10, 0);

        gbc.gridy = 3;
        JButton botao3 = new JButton("Poker");
        botao3.setFont(new Font("Arial", Font.BOLD, 16));
        botao3.setPreferredSize(new Dimension(100, 30));
        botao3.setFocusPainted(false);
        swingPanel.add(botao3, gbc);

        gbc.insets = new Insets(5, 0, 10, 0);

        gbc.gridy = 4;
        JButton botao4 = new JButton("BackJack");
        botao4.setFont(new Font("Arial", Font.BOLD, 16));
        botao4.setPreferredSize(new Dimension(100, 30));
        botao4.setFocusPainted(false);
        swingPanel.add(botao4, gbc);

        gbc.insets = new Insets(5, 0, 10, 0);

        gbc.gridy = 5;
        JButton botao5 = new JButton("Caça Níquel");
        botao5.setFont(new Font("Arial", Font.BOLD, 16));
        botao5.setPreferredSize(new Dimension(100, 30));
        botao5.setFocusPainted(false);
        swingPanel.add(botao5, gbc);

        // conectar JPanel a JFrame
        swingFrame.add(swingPanel);

        // fazer visível JFrame
        swingFrame.setVisible(true);
    }
}