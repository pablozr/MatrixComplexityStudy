public class MatrizEsparsaEstatica {
    private int linhas;
    private int colunas;
    private int[][] matriz;

    public MatrizEsparsaEstatica(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.matriz = new int[linhas][colunas];
    }

    //1 - Inserir um elemento
    public void insertElement(int linha, int coluna, int valor) {
        matriz[linha][coluna] = valor;
    }

    //2 - Remover um elemento
    public void removeElement(int linha, int coluna) {
        matriz[linha][coluna] = 0;
    }

    //3 - Busca por um elemento específico (pode ser feito como booleano e retornando true/false)
    public boolean searchElement(int elem) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] == elem){
                    System.out.println("Elemento " + elem + " encontrado, na posiçã  o [" + i + ", " + j + "]");
                    return true;
                }
            }
        }

        System.out.println("Elemento não encontrado");
        return false;
    }

    //4 - Impressão da matriz
    public void printMatriz() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.printf("[%d][%d]: [%d] \n", i, j, matriz[i][j]);
            }
        }
    }

    //5 - Representar uma matriz vazia
    public void printMatrizVazia (){
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.printf("[%d][%d]: 0 \n", i, j);
            }
        }
    }

    //6 - Verificar se é uma matriz vazia
    public boolean isVazia() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //7 - Verificar se é uma matriz diagonal (só tem elementos na diagonal principal)
    public boolean isDiagonal() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i != j && matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //8 - Verificar se é uma matriz linha (só tem elementos em uma única linha)
    public boolean isLinha() {
        boolean encontrouLinha = false;
        for (int i = 0; i < linhas; i++) {
            boolean linhaNaoVazia = false;
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] != 0) {
                    linhaNaoVazia = true;
                }
            }
            if (linhaNaoVazia) {
                if (encontrouLinha) {
                    return false;
                }
                encontrouLinha = true;
            }
        }
        return encontrouLinha;
    }

    //9 - Verificar se é uma matriz coluna (só tem elementos em uma única coluna)
    public boolean isColuna() {
        boolean encontrouColuna = false;
        for (int j = 0; j < colunas; j++) {
            boolean colunaNaoVazia = false;
            for (int i = 0; i < linhas; i++) {
                if (matriz[i][j] != 0) {
                    colunaNaoVazia = true;
                }
            }
            if (colunaNaoVazia) {
                if (encontrouColuna) {
                    return false;
                }
                encontrouColuna = true;
            }
        }
        return encontrouColuna;
    }

    //10 - Verificar se é uma matriz triangular inferior (só tem elementos da diagonal
    //principal para baixo)
    public boolean isTriangularInferior() {
        for (int i = 0; i < linhas; i++) {
            for (int j = i + 1; j < colunas; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
