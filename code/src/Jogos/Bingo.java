package Jogos;

import Entidades.Jogador;
import Utilidades.Utils;
import Utilidades.ValorInvalidoException;

import java.util.*;

public class Bingo extends Jogo {
    private Set<Integer> numerosSorteados;
    private List<Cartela> jogadores;
    private double resultado;
    private double aposta;

    public Bingo(boolean estadoJogo) {
        super(estadoJogo);
        this.numerosSorteados = new HashSet<>();
        this.jogadores = new ArrayList<>();
    }

    public void sortearNumero() {
        Random random = new Random();
        int numero = random.nextInt(50) + 1;
        while (numerosSorteados.contains(numero)) {
            numero = random.nextInt(50) + 1;
        }
        numerosSorteados.add(numero);
        System.out.println("Número sorteado: " + numero);
        for (Cartela cartela : jogadores) {
            cartela.marcarNumero(numero);
        }
    }

    public boolean verificarVencedor(Cartela cartela) {
        return cartela.verificarCartelaCompleta(numerosSorteados);
    }

    public void adicionarJogadorBingo(Cartela cartela) {
        jogadores.add(cartela);
        super.adicionarJogador();
    }

    public void jogar(Scanner scanner, Jogador jogador) throws ValorInvalidoException {
        super.setarJogadores();
        this.jogadores.clear();
        this.numerosSorteados.clear();

        System.out.println("""
                       -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- B I N G O -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                             LEMBRE-SE QUE SE VOCÊ INTERROMPER O SORTEIO NO MEIO DA EXECUÇÃO,
                          NÃO NOS RESPONSABILIZAREMOS PELO REEMBOLSO DOS SEUS CRÉDITOS APOSTADOS

                       Primeiramente, nos informe a quantidade de jogadores que jogarão:""");
        int numJogadores = scanner.nextInt();
        scanner.nextLine();

        try {
            for (int i = 1; i <= numJogadores; i++) {
                System.out.println("Nome do jogador " + i + ":");
                String nome = scanner.nextLine();

                System.out.println("Valor apostado pelo jogador " + i + ":");
                double aposta = scanner.nextDouble();
                scanner.nextLine();

                if (i == 1) {
                    if (!Utils.verificarSaldoAposta(aposta, jogador)) {
                        throw new ValorInvalidoException("Sem saldo na carteira, ou valor inválido.");
                    } else {
                        jogador.retirarCreditos(aposta);
                        setAposta(aposta);
                        Cartela cartela = new Cartela(false, nome, aposta);
                        adicionarJogadorBingo(cartela);
                        System.out.println(cartela.imprimirCartela()); // Imprime a cartela
                    }
                } else {
                    Cartela cartela = new Cartela(false, nome, aposta);
                    adicionarJogadorBingo(cartela);
                    System.out.println(cartela.imprimirCartela()); // Imprime a cartela
                }
            }
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }

        if (getNumeroJogadores() > 0) {
            iniciarJogo();
            while (super.getEstado()) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Sortear próximo número? (s/n)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    sortearNumero(); // Sorteia o número e atualiza as cartelas

                    // Imprime todas as cartelas após o sorteio
                    for (Cartela cartela : jogadores) {
                        System.out.println(cartela.imprimirCartela()); // Imprime cada cartela
                    }

                    // Verifica se há vencedores
                    for (int i = 0; i < jogadores.size(); i++) {
                        Cartela cartela = jogadores.get(i);

                        if (verificarVencedor(cartela)) {
                            if (i == 0) {
                                setResultadoPartida(cartela.getResultado());
                                jogador.depositarCreditos(resultado);
                            }

                            System.out.println(cartela.getNome() + " venceu!");
                            System.out.println("Valor ganho pelo jogador " + cartela.getNome() + ": " + cartela.getResultado());
                            System.out.println(cartela.imprimirCartela()); // Imprime a cartela do vencedor
                            finalizarJogo();
                            break;
                        }
                    }
                } else if (resposta.equalsIgnoreCase("n")) {
                    System.out.println("Interrompendo o sorteio!");
                    setEstado(false);
                }
            }
        }
    }

    @Override
    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Partida finalizada! Obrigado por jogar conosco.");
    }

    @Override
    public void iniciarJogo () {
        super.setEstado(true);
        System.out.println("Bingo iniciado, preparem as suas cartelas!");
    }

    @Override
    public String imprimir () {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor apostado do jogador 1: " + aposta + "\n" +
                "Numeros sorteados no último jogo: " + numerosSorteados + "\n" +
                "Resultado do jogador 1 (em créditos): " + resultado + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }

    public void setAposta(double aposta) {
        this.aposta += aposta;
    }

    public void setResultadoPartida(double resultado) {
        this.resultado += resultado;
    }

    public List<Cartela> getJogadores() {
        return new ArrayList<>();
    }

    public Set<Integer> getNumerosSorteados() {
        return numerosSorteados;
    }
}