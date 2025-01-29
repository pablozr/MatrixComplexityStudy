import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        int[] tamanhos = {10, 20, 30, 40, 50, 100, 200, 500, 1000};

        try (FileWriter writer = new FileWriter("desempenho_matrizes.csv", StandardCharsets.UTF_8)) {
            writer.write("Tamanho;Metodo;Classe;TempoMedio\n");

            for (int tamanho : tamanhos) {
                System.out.println("Testando matrizes de tamanho: " + tamanho);

                MatrizEsparsaEstatica estatica = new MatrizEsparsaEstatica(tamanho, tamanho);
                estatica.gerarMatriz(tamanho);
                MatrizEsparsaLinkedList encadeada = new MatrizEsparsaLinkedList(tamanho);
                encadeada.gerarMatriz(tamanho);

                String[] metodos = {"multiplicaMatrizes", "obterTransposta", "somarMatrizes"};

                for (String metodo : metodos) {
                    long tempoTotal = 0;

                    for (int i = 0; i < 10; i++) {
                        long startTime = System.nanoTime();

                        if (metodo.equals("multiplicaMatrizes")) {
                            estatica.multiplicaMatrizes(estatica);
                        } else if (metodo.equals("obterTransposta")) {
                            estatica.obterTransposta();
                        } else if (metodo.equals("somarMatrizes")) {
                            estatica.somarMatrizes(estatica);
                        }

                        long endTime = System.nanoTime();
                        tempoTotal += (endTime - startTime);
                        System.out.println(i);
                    }

                    long tempoMedio = tempoTotal / 10;
                    double tempoEmSegundos = tempoMedio / 1_000_000_000.0;
                    String tempoFormatado = String.format("%.6f", tempoEmSegundos);

                    writer.write(tamanho + ";" + metodo + ",MatrizEsparsaEstatica;" + tempoFormatado + "\n");

                    long tempoTotalEncadeada = 0;
                    for (int i = 0; i < 10; i++) {
                        long startTime = System.nanoTime();

                        if (metodo.equals("multiplicaMatrizes")) {
                            encadeada.multiplicaMatrizes(encadeada);
                        } else if (metodo.equals("obterTransposta")) {
                            encadeada.obterTransposta();
                        } else if (metodo.equals("somarMatrizes")) {
                            encadeada.somarMatrizes(encadeada);
                        }

                        long endTime = System.nanoTime();
                        tempoTotalEncadeada += (endTime - startTime);
                    }
                    long tempoMedioEncadeada = tempoTotalEncadeada / 10;
                    double tempoEmSegundosEncadeada = tempoMedioEncadeada / 1_000_000_000.0;
                    String tempoFormatadoEncadeada = String.format("%.6f", tempoEmSegundosEncadeada);

                    writer.write(tamanho + ";" + metodo + ";MatrizEsparsaLinkedList;" + tempoFormatadoEncadeada + "\n");
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
