package Jogos;

import Entidades.Jogador;
import Utilidades.Utils;
import Utilidades.ValorInvalidoException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Blackjack extends Jogo {
    private double resultado; // Resultado da jogada
    private double valorApostado; // Valor apostado na rodada
    private int dealerPontos; // Pontos do dealer

    public Blackjack(int numeroJogadores, boolean estadoDoJogo) {
        super(numeroJogadores, estadoDoJogo);
    }

    public void jogar(Scanner leitor, Jogador jogador) throws ValorInvalidoException {
        Random r = new Random(System.currentTimeMillis());
        resultado = 0;
        valorApostado = 0; //NENHUMA APOSTA FEITA

        System.out.println("-_-_-_-_-_-_-_-_-_- B L A C K J A C K -_-_-_-_-_-_-_-_-_-");

        boolean acessoBJ = true;
        while (acessoBJ) {
            System.out.println("""
                    
                    Opções:
                    [1] - Apostar
                    [2] - Jogar

                    [0] - Voltar ao início""");

            try {
                switch (leitor.nextInt()) {
                    case 1:
                        try {
                            System.out.println("Qual é o valor que você deseja apostar?:");
                            double valor = leitor.nextDouble();
                            if (Utils.verificarSaldoAposta(valor, jogador)) {
                                valorApostado += valor;
                                System.out.println("Você apostou " + valor + " créditos.");
                                jogador.retirarCreditos(valor);
                            } else {
                                throw new ValorInvalidoException("Valor inválido, tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Por favor, insira um número válido.");
                            leitor.nextLine();
                        } catch (ValorInvalidoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            if (valorApostado <= 0) {
                                throw new ValorInvalidoException("Você precisa fazer uma aposta antes de jogar.");
                            } else {
                                iniciarJogo();
                                int jogadorPontos = calcularPontos(r); // Jogador recebe pontos
                                dealerPontos = calcularPontos(r); // Dealer recebe pontos

                                System.out.println("Seus pontos: " + jogadorPontos);
                                System.out.println("Pontos do dealer: " + dealerPontos);

                                boolean continuar = true;
                                while (continuar && jogadorPontos <= 21) {
                                    System.out.println("""
                                    Deseja comprar mais uma carta?
                                    [1] - Sim
                                    [2] - Não
                                    
                                    """);
                                    int escolha = leitor.nextInt();
                                    if (escolha == 1) {
                                        jogadorPontos += calcularPontos(r, 1); // Jogador compra mais uma carta
                                        System.out.println("Seus pontos: " + jogadorPontos);
                                    } else {
                                        continuar = false;
                                    }
                                }

                                if (jogadorPontos > 21) {
                                    System.out.println("Você estourou! Dealer vence.");

                                } else {
                                    while (dealerPontos < jogadorPontos) {
                                        dealerPontos += calcularPontos(r, 1); // Dealer compra mais cartas
                                        System.out.println("Pontos do dealer: " + dealerPontos);
                                    }

                                    if (dealerPontos > 21) {
                                        System.out.println("Você venceu! Valor da aposta dobrado!");
                                        resultado = valorApostado;
                                        resultado *= 2;
                                    } else if (jogadorPontos == dealerPontos) {
                                        System.out.println("Empate!");
                                    } else {
                                        System.out.println("Você perdeu!");
                                    }
                                }
                                jogador.depositarCreditos(resultado);
                                finalizarJogo();
                                acessoBJ = false;
                                break;
                            }
                        } catch (ValorInvalidoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Voltando à página inicial....");
                        return;

                    default:
                        throw new ValorInvalidoException("Opção inválida, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro." + "\n");
                leitor.nextLine();
            }
        }
    }

    // Método para calcular os pontos, considerando os ases como 1 ou 11
    private int calcularPontos(Random r) {
        return calcularPontos(r, 2); // Garante a distribuição de 2 cartas inicialmente
    }

    // Método sobrecarregado para calcular pontos com um número específico de cartas
    private int calcularPontos(Random r, int numCartas) {
        int pontos = 0;
        int ases = 0;

        for (int i = 0; i < numCartas; i++) {
            int carta = r.nextInt(10) + 1; // Gera uma carta entre 1 e 10
            if (carta == 1) {
                ases++;
                carta = 11; // Inicialmente, considera o Ás como 11
            }
            pontos += carta;
        }

        // Ajusta o valor dos ases se necessário
        while (pontos > 21 && ases > 0) {
            pontos -= 10; // Converte um Ás de 11 para 1
            ases--;
        }

        return pontos;
    }

    @Override
    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Terminando sua jogada no Blackjack!\n");
    }

    @Override
    public void iniciarJogo() {
        super.setEstado(true);
        System.out.println("Vamos começar o jogo! Boa sorte.\n");
    }

    @Override
    public String imprimir() {
        double saldoFinal = Math.max(resultado, 0);
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Último valor apostado: " + valorApostado + "\n" +
                "Resultado da ultima partida (em créditos): " + saldoFinal + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }
}