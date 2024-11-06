package Utilidades;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorTXT {
    public static void main(String[] args) {
        try {
            FileReader arquivo = new FileReader("C:/Users/gabri/OneDrive/Documentos/GitHub/POO/Cassino-Java/code/src/Utilidades/dados.txt");
            BufferedReader buffer =
                    new BufferedReader(arquivo);
            String str;
            while ((str = buffer.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
