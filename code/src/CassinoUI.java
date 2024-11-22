import Entidades.Jogador;
import Utilidades.Swing.Swing;

import javax.swing.*;


public class CassinoUI {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do jogador:");
        String idadeInput = JOptionPane.showInputDialog("Digite a idade do jogador:");
        String creditosInput = JOptionPane.showInputDialog("Digite o valor inicial de créditos:");

        Jogador jogador;
        try {
            int idade = Integer.parseInt(idadeInput);
            double creditosIniciais = Double.parseDouble(creditosInput);

            if (idade < 18) {
                JOptionPane.showMessageDialog(null,
                        "Jogadores com menos de 18 anos não podem jogar.");
                return;
            }

            jogador = new Jogador(nome, idade, creditosIniciais);
            JOptionPane.showMessageDialog(null, "Jogador " + jogador.getNome()
                    + " criado com " + jogador.getCreditos() + " créditos.");
            Swing.tocarSomEntrada();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Idade ou créditos inválidos. Tente novamente.");
            return;
        }

        boolean opcao7Disponivel = nome.equalsIgnoreCase("Marina");
        Swing.mostrarMenuPrincipal(jogador, opcao7Disponivel);
    }
}