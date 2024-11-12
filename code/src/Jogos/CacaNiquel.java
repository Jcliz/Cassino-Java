package Jogos;

import Entidades.Jogador;
import Utilidades.Utils;
import Utilidades.ValorInvalidoException;

import java.util.*;

public class CacaNiquel extends Jogo {

    private ArrayList<String> simbolos = new ArrayList<>(Arrays.asList(
            "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Diamante", "Diamante", "Diamante", "Diamante", "Diamante", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer"
    ));
    private double saldo;
    private double ficha;
    private double valorAposta;
    private double compraFicha;

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
                    System.out.println("Você acertou 3 Cerejas!" + "\n");
                    saldo += 100;
                    break;
                case "Limão":
                    System.out.println("Você acertou 3 Limões!" + "\n");
                    saldo += 300;
                    break;
                case "Uva":
                    System.out.println("Você acertou 3 Uvas!" + "\n");
                    saldo += 500;
                    break;
                case "Melancia":
                    System.out.println("Você acertou 3 Melancias!" + "\n");
                    saldo += 700;
                    break;
                case "Laranja":
                    System.out.println("Você acertou 3 Laranjas!" + "\n");
                    saldo += 900;
                    break;
                case "Sino":
                    System.out.println("Você acertou 3 Sinhos!" + "\n");
                    saldo += 1200;
                    break;
                case "BAR":
                    System.out.println("Você acertou 3 BARS!" + "\n");
                    saldo += 2000;
                    break;
                case "Ferradura":
                    System.out.println("Você acertou 3 Ferraduras!" + "\n");
                    saldo += 3000;
                    break;
                case "Estrela":
                    System.out.println("Você acertou 3 Estrelas!" + "\n");
                    saldo += 4000;
                    break;
                case "Joker":
                    System.out.println("Você acertou 3 Jokers!" + "\n");
                    saldo += 10000;
                    break;
                case "Diamante":
                    System.out.println("Você acertou 3 Diamantes!" + "\n");
                    saldo += 25000;
                    break;
                case "Lancer":
                    System.out.println("Você acertou 3 Lancers!" + "\n");
                    saldo += 100000;
                    break;
                default:
                    System.out.println("Três símbolos iguais, mas não reconhecidos." + "\n");
            }

        } else {
            System.out.println("A sequência não foi acertada. Boa sorte na próxima!" + "\n");
            saldo -= ficha;
        }
    }

    //FAZER A VERIFICAÇÃO DE SALDO NO MAIN COM UM MÉTODO DO UTILS
    public void jogar(Scanner leitor, Jogador jogador) throws ValorInvalidoException {
        System.out.println("-_-_-_-_-_- C A Ç A  N Í Q U E I S -_-_-_-_-_-" + "\n");
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
                        try {
                            System.out.println("\n" + "Cada ficha tem o valor de " + ficha + " crédito(s)");
                            System.out.println("\n" + "Quantas fichas você deseja adiquirir?:");
                            int compraFichas = leitor.nextInt();
                            double valorApostado = compraFichas * ficha;

                            //TRATAMENTO DE EXCESSÃO
                            if (Utils.verificarSaldoAposta(valorApostado, jogador)) {
                                System.out.println("Adicionando " + compraFichas + " fichas(s) ao seu saldo!");
                                compraFicha = compraFichas;
                                valorAposta = valorApostado;
                                jogador.retirarCreditos(valorAposta);
                                saldo += valorAposta;
                            } else {
                                throw new ValorInvalidoException("Saldo inválido.");
                            }
                        } catch (ValorInvalidoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Você deseja rodar quantas vezes?:");
                        int quantasVezes = leitor.nextInt();

                        if (compraFicha > 0) {
                            System.out.println("-_-_-_- INICIANDO OS JOGOS! -_-_-_-");
                            for (int i = 0; i < quantasVezes; i++) {
                                System.out.println("Quantidade de fichas restantes: " + compraFicha);
                                compraFicha--;
                                sortearSimbolos();
                                Thread.sleep(1500);

                                if (compraFicha == 0) {
                                    System.out.println("""
                                            -_-_-______-_-_-______-_-_-______-_-_-______-_-_-
                                                   Suas fichas acabaram, compre mais!
                                            ______-_-_-______-_-_-______-_-_-______-_-_-______
                                            """);
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Seu saldo acabou, ou você não possui fichas suficientes." + "\n");
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
        } catch (InputMismatchException e) {
            System.out.println("Por favor, insira um valor válido.");
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
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
                "Valor Apostado: " + valorAposta + "\n" +
                "Resultado: " + saldo + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }
}