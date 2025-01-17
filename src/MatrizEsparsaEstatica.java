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

    //3 - Busca por um elemento específico
    public int searchElement(int i, int j) {
        int N = 0;

        if(i > linhas || j > colunas)
            System.out.println("Erro ao buscar elemento");

        for(int I = 0; I < linhas; I++) {
            for(int J = 0; J < colunas; J++)
                if(i == I && j == J)
                    N = matriz[I][J];
        }

        return N;
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

    /* 11 - Verificar se é uma matriz triangular superior (só tem elementos da diagonal
    principal para cima)*/
    public boolean isTriangularSuperior() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < i; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /*12. Verificar se a matriz é simétrica; (A[i,j] = A[j,i] para todo i, j);*/
    public boolean isSimetrica(){
        for (int i = 0; i < linhas; i++){
            for (int j = i + 1; j < colunas; j++){
                if (matriz[i][j] != matriz[j][i]){
                    return false;
                }
            }
        }
        return true;
    }

    /*13. Somar duas matrizes esparsas;*/
    public MatrizEsparsaEstatica somarMatrizes(MatrizEsparsaEstatica matriz2) {

        //matriz resultante
        MatrizEsparsaEstatica matrizResultante = new MatrizEsparsaEstatica(this.linhas, this.colunas);

        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                int valorSoma = this.matriz[i][j] + matriz2.matriz[i][j];
                matrizResultante.insertElement(i, j, valorSoma);
            }
        }

        return matrizResultante;
    }

    /*14. Multiplicar duas matrizes esparsas;*/
    public MatrizEsparsaEstatica multiplicaMatrizes(MatrizEsparsaEstatica matriz2){
        // Cria a matriz resultante
        MatrizEsparsaEstatica matrizResultante = new MatrizEsparsaEstatica(this.linhas, matriz2.colunas);

        // Realiza a multiplicação
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < matriz2.colunas; j++) {
                int valorProduto = 0;
                for (int k = 0; k < this.colunas; k++) {
                    valorProduto += this.matriz[i][k] * matriz2.matriz[k][j];
                }
                if (valorProduto != 0) { // Apenas insere elementos diferentes de zero
                    matrizResultante.insertElement(i, j, valorProduto);
                }
            }
        }

        return matrizResultante;
    }

    /*15. Obter a matriz transposta*/
    public MatrizEsparsaEstatica obterTransposta() {
        MatrizEsparsaEstatica matrizTransposta = new MatrizEsparsaEstatica(this.colunas, this.linhas);

        // Preenche a matriz transposta
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                matrizTransposta.insertElement(j, i, this.matriz[i][j]);
            }
        }

        return matrizTransposta;
    }
}
