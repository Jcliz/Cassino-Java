package Entidades;

import java.io.*;

public class Serializacao {
    public static void salvarPessoa(Jogador j) throws IOException {
        FileOutputStream fos = new FileOutputStream("jogador.txt");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(j);
        os.close();
        fos.close();
    }

    public static Jogador carregarJogador() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("jogador.txt");
        ObjectInputStream is = new ObjectInputStream(fis);
        Jogador j = (Jogador) is.readObject();
        is.close();
        fis.close();
        return j;
    }
}