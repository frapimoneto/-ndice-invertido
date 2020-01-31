package pooiii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import pooiii.inverted.index.InvertedIndex;
import pooiii.inverted.index.StopWords;

public class Main {

    private final InvertedIndex ii;
    private final StopWords stopWords;

    public Main() {
        ii = new InvertedIndex();
        stopWords = new StopWords();
    }

    // percorre a lista de arquivos contida no diretório previamente selecionado
    public void readFiles(File directory) {
        for (File f : directory.listFiles()) {
            System.out.println("> " + f.getAbsolutePath());
            if (!f.isDirectory()) {
                parseFile(f);
            }
        }
    }

    // lê o conteúdo de um arquivo linha a linha ignorando termos de tamanho 1 e 'stopwords' 
    public void parseFile(File f) {
        BufferedReader reader; // serve para fazer a leitura dos arquivos 
        StringTokenizer st; //percorre a string e quebra em tokens (palavras separadas por um caractere delimitador)
        try {
            reader = new BufferedReader(new FileReader(f)); // estanciando um novo bufferReader
            String line = reader.readLine(); // a linha "line" recebe o texto inteiro de uma linha
            while (line != null) {

                st = new StringTokenizer(line, " "); // quebra a string a cada espaço
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    token = clearToken(token); // substitui ou tira caracteres que possam dificultar a busca
                    if (token.length() > 1 && !stopWords.contains(token)) { // pega as palavras úteis
                        ii.add(token, f.getAbsolutePath()); // adiciona a palavra e o caminho
                    }
                }
                line = reader.readLine(); // recebe a proxima linha
            }
            reader.close(); // fecha o buffer de leitura
        } catch (IOException e) {
            e.printStackTrace(); //escreve o erro
        }
    }

    // remove caracteres especiais dos tokens
    private String clearToken(String token) {
        String st = token.replace(".", "");
        st = st.replace("(", "");
        st = st.replace(")", "");
        st = st.replace("!", "");
        st = st.replace("?", "");
        st = st.replace(":", "");
        st = st.replace(";", "");
        st = st.replace(",", "");
        st = st.replace("\'", "");
        st = st.replace("\"", "");
        st = st.toLowerCase();
        st = st.replace("à", "a");
        st = st.replace("ã", "a");
        st = st.replace("á", "a");
        st = st.replace("é", "e");
        st = st.replace("ê", "e");
        st = st.replace("í", "i");
        st = st.replace("ó", "o");
        st = st.replace("õ", "o");
        st = st.replace("ú", "u");
        st = st.replace("ç", "c");
        return st;
    }

    // imprime o índice invertido obtido no processo
    public void printIndex() {
        SortedSet<String> tokens = new TreeSet<String>(ii.keySet());
        for (String token : tokens) {
            System.out.println("> " + token);
            for (String path : ii.get(token).keySet()) {
                System.out.println("* " + path + "\t\t" + ii.get(token).get(path));
            }
        }
    }

    // seleciona o diretório e repassa para a classe Main que inicia a indexação
    public static void main(String[] args) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);

        File dir = fc.getSelectedFile();

        if (fc.getSelectedFile() != null) { //caso tenha um diretorio selecionado
            Main m = new Main();

            m.readFiles(dir);

            m.printIndex();
        }
    }
}