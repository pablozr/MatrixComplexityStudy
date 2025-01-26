import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
<<<<<<< Updated upstream
        //TESTES ESPARSA NORMAL

        /*MatrizEsparsaEstatica matriz = new MatrizEsparsaEstatica(3, 3);
=======
        int[] tamanhos = {10, 20, 30, 40, 50, 100, 200, 500, 1000, 10000};
>>>>>>> Stashed changes

        try (FileWriter writer = new FileWriter("desempenho_matrizes.csv", StandardCharsets.UTF_8)) {
            writer.write("Tamanho,Metodo,Classe,TempoMedio\n");

<<<<<<< Updated upstream
        //transposta
        //matriz.obterTransposta().printMatriz();
=======
            for (int tamanho : tamanhos) {
                System.out.println("Testando matrizes de tamanho: " + tamanho);

                MatrizEsparsaEstatica estatica = new MatrizEsparsaEstatica(tamanho, tamanho);
                estatica.gerarMatriz(tamanho);
                MatrizEsparsaLinkedList encadeada = new MatrizEsparsaLinkedList(tamanho);

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
>>>>>>> Stashed changes

                    writer.write(tamanho + ";" + metodo + ",MatrizEsparsaEstatica;" + tempoFormatado + "\n");

<<<<<<< Updated upstream
        //triangular superior
        matriz.isTriangularSuperior();*/

        //TESTES ESPARSA LISTA ENCADEADA

        MatrizEsparsaLinkedList matriz = new MatrizEsparsaLinkedList(3);

        matriz.insertElement(0,0, 1);
        matriz.insertElement(0,1, 2);
        matriz.insertElement(0,2, 3);
        matriz.insertElement(1,0, 4);
        matriz.insertElement(1,1, 5);
        matriz.insertElement(1,2, 6);
        matriz.insertElement(2,0, 7);
        matriz.insertElement(2,1, 8);
        matriz.insertElement(2,2, 9);

        matriz.printMatriz();

        matriz.removeElement(1,1);

        System.out.println("-----------------------------------------------");

        matriz.printMatriz();

        System.out.println(matriz.searchElement(2, 2));
=======
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
>>>>>>> Stashed changes
    }
}
