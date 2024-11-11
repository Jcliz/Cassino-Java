package Jogos;

import Entidades.Jogador;
import Utilidades.ValorInvalidoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class CacaNiquel extends Jogo {

    private ArrayList<String> simbolos = new ArrayList<>(Arrays.asList(
            "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Diamante", "Diamante", "Diamante", "Diamante", "Diamante", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer"
    ));
    private double saldo;
    private double ficha;
    private double valorAposta;
    private double aposta;

    public CacaNiquel(int numeroJogadores, boolean estadoDoJogo, double ficha) {
        super(numeroJogadores, estadoDoJogo);
        this.ficha = ficha;
    }

    public void sortearSimbolos() {
        Collections.shuffle(simbolos);
        String simbolo1 = simbolos.get(0);
        String simbolo2 = simbolos.get(1);
        String simbolo3 = simbolos.get(2);
        System.out.println(simbolo1);
        System.out.println(simbolo2);
        System.out.println(simbolo3);

        if (simbolo1.equals(simbolo2) && simbolo2.equals(simbolo3)) {
            switch (simbolo1) {
                case "Cereja":
                    System.out.println("Você acertou 3 Cerejas!");
                    saldo += 100;
                    break;
                case "Limão":
                    System.out.println("Você acertou 3 Limões!");
                    saldo += 300;
                    break;
                case "Uva":
                    System.out.println("Você acertou 3 Uvas!");
                    saldo += 500;
                    break;
                case "Melancia":
                    System.out.println("Você acertou 3 Melancias!");
                    saldo += 700;
                    break;
                case "Laranja":
                    System.out.println("Você acertou 3 Laranjas!");
                    saldo += 900;
                    break;
                case "Sino":
                    System.out.println("Você acertou 3 Sinhos!");
                    saldo += 1200;
                    break;
                case "BAR":
                    System.out.println("Você acertou 3 BARS!");
                    saldo += 2000;
                    break;
                case "Ferradura":
                    System.out.println("Você acertou 3 Ferraduras!");
                    saldo += 3000;
                    break;
                case "Estrela":
                    System.out.println("Você acertou 3 Estrelas!");
                    saldo += 4000;
                    break;
                case "Joker":
                    System.out.println("Você acertou 3 Jokers!");
                    saldo += 10000;
                    break;
                case "Diamante":
                    System.out.println("Você acertou 3 Diamantes!");
                    saldo += 25000;
                    break;
                case "Lancer":
                    System.out.println("Você acertou 3 Lancers!");
                    saldo += 100000;
                    break;
                default:
                    System.out.println("Três símbolos iguais, mas não reconhecidos.");
            }

        } else {
            System.out.println("A sequência não foi acertada. Boa sorte na próxima!");
        }
    }

    //FAZER A VERIFICAÇÃO DE SALDO NO MAIN COM UM MÉTODO DO UTILS
    public void jogar(Scanner leitor, Jogador jogador) throws ValorInvalidoException {
        iniciarJogo();

        try {
            while (super.getEstado()) {
                System.out.println("""
                        Opções do jogo:
                        
                        [1] - Comprar fichas
                        [2] - Jogar
                        
                        [0] - Voltar
                        """);

                //TRATAMENTO DE EXCESSÃO
                switch (leitor.nextInt()) {
                    case 1:
                        System.out.println("\n" + "Cada ficha tem o valor de " + ficha + " crédito(s)");
                        System.out.println("\n" + "Quantas fichas você deseja adiquirir?:");
                        int compraFichas = leitor.nextInt();
                        double valorApostado = compraFichas * ficha;
                        this.aposta += valorApostado;

                        //TRATAMENTO DE EXCESSÃO
                        if (compraFichas > 0) {
                            System.out.println("Adicionando " + compraFichas + " fichas(s) ao seu saldo!");
                            valorAposta = compraFichas * ficha;
                            saldo = valorAposta;
                        } else {
                            throw new ValorInvalidoException("Opção inválida, tente novamente.");
                        }
                        break;

                    case 2:
                        System.out.println("Você deseja rodar quantas vezes?:");
                        int quantasVezes = leitor.nextInt();
                        double rodada = quantasVezes * ficha;

                        if (rodada >= saldo) {
                            System.out.println("-_-_-_- INICIANDO OS JOGOS! -_-_-_-");
                            for (int i = 0; i < quantasVezes; i++) {
                                sortearSimbolos();
                                Thread.sleep(1500);
                            }
                        } else {
                            System.out.println("Seu saldo acabou, ou não atende ao valor da(s) ficha(s)! :(");
                        }
                        break;

                    case 0:
                        finalizarJogo();
                        break;

                    default:
                        throw new ValorInvalidoException("Opção inválida, tente novamente.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Finalizando sua jogatina na máquina de Caça Níqueis!");
    }

    @Override
    public void iniciarJogo() {
        super.setEstado(true);
        System.out.println("Iniciando o caça níquel!");
    }

    @Override
    public String imprimir () {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor Apostado: " + aposta + "\n" +
                "Resultado: " + saldo + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }
}