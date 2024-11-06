package Entidades;

import java.io.*;

public class Serializacao {
        public static void main(String[] args) throws IOException,
                ClassNotFoundException {
            Jogador j1 = new Jogador("João", "01/01/2001", 0);
            salvarPessoa(j1);
            Jogador j2 = carregarJogador();
            System.out.println(j2);
        }

        public static void salvarPessoa(Jogador j) throws IOException {
            FileOutputStream fos = new FileOutputStream("jogador.txt");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(j);
            os.close();
            fos.close();

        }

    public static Jogador carregarJogador() throws IOException,
            ClassNotFoundException {
                FileInputStream fis = new FileInputStream("jogador.txt");
        ObjectInputStream is = new ObjectInputStream(fis);
        Jogador j = (Jogador) is.readObject();
        is.close();
        fis.close();
        return j;
    }
    }
