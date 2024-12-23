public class MatrizEsparsaEstatica {
    private final int linhas;
    private final int colunas;
    private final int[][] matriz;

    public MatrizEsparsaEstatica(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.matriz = new int[linhas][colunas];
    }
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
}
