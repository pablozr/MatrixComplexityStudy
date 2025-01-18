public class MatrizEsparsaLinkedList {
    private Elo[] linhas;

    protected class Elo {
        protected int coluna;
        protected int valor;
        protected Elo prox;

        public Elo(int coluna, int valor) {
            this.coluna = coluna;
            this.valor = valor;
            this.prox = null;
        }
    }

    public MatrizEsparsaLinkedList(int numLinhas) {
        linhas = new Elo[numLinhas];
    }
    
    //1 - Inserir um elemento
    public void insertElement(int linha, int coluna, int valor) {
        if (linha < 0 || linha >= linhas.length) {
            throw new IllegalArgumentException("Linha fora do intervalo permitido.");
        }

        Elo novo = new Elo(coluna, valor);

        if (linhas[linha] == null) {
            linhas[linha] = novo;
            return;
        }

        Elo atual = linhas[linha];
        Elo anterior = null;

        while (atual != null && atual.coluna < coluna) {
            anterior = atual;
            atual = atual.prox;
        }

        if (atual != null && atual.coluna == coluna) {
            atual.valor = valor;
        } else {
            if (anterior == null) {
                novo.prox = linhas[linha];
                linhas[linha] = novo;
            } else {
                novo.prox = atual;
                anterior.prox = novo;
            }
        }
    }
    
    //2 - Remover um elemento
    public void removeElement(int linha, int coluna) {
        if (linha < 0 || linha >= linhas.length) {
            throw new IllegalArgumentException("Linha fora do intervalo permitido.");
        }

        Elo atual = linhas[linha];
        Elo anterior = null;

        while (atual != null && atual.coluna != coluna) {
            anterior = atual;
            atual = atual.prox;
        }

        if (atual != null) {
            if (anterior == null)
                linhas[linha] = atual.prox;
            else
                anterior.prox = atual.prox;

        }
    }
    
    //3 - Buscar por um elemento específico
    public int searchElement(int linha, int coluna) {
        if (linha < 0 || linha >= linhas.length)
            throw new IllegalArgumentException("Índice da linha está fora do intervalo válido.");


        Elo p = linhas[linha];
        if (p == null)
            throw new IllegalArgumentException("A linha especificada está vazia.");

        int cont = 0;
        for (Elo x = p; x != null; x = x.prox, cont++) {
            if (cont == coluna)
                return x.valor;
        }

        throw new IllegalArgumentException("Índice da coluna está fora do intervalo válido.");
    }


    //4 - Impressão da matriz
    public void printMatriz() {
        for (int i = 0; i < linhas.length; i++) {
            Elo p = linhas[i];

            while (p != null) {
                System.out.printf("[%d][%d]: %d%n", i, p.coluna, p.valor);
                p = p.prox;
            }
        }
    }

    //5 - Representar uma matriz vazia
    public void printEmptyMatriz(){
        System.out.println("null");
    }

    //11 - Verificar se é uma matriz triangular superior (só tem elementos da diagonal principal para cima);
    public boolean isTriangularSuperior() {
        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                if (atual.coluna < i) {//se tiver algum que coluna < i, o elemento tá abaixo da diagonal principal
                    return false;
                }
                atual = atual.prox;
            }
        }
        return true; // Todos os elementos abaixo da diagonal principal são zeros
    }

    //12 - Verificar se a matriz é simétrica; (A[i,j] = A[j,i] para todo i, j)
    public boolean isSimetrica() {
        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                int j = atual.coluna;
                int valor = atual.valor;

                //Verifica se existe o elemento simétrico (j, i) e se o valor é igual
                if (!temValorNaPosicao(j, i, valor)) {
                    return false;
                }

                atual = atual.prox;
            }
        }
        return true;
    }

    //Método auxiliar para verificar se naquela linha e coluna tem o valor
    private boolean temValorNaPosicao(int linha, int coluna, int valor) {
        if (linha >= linhas.length) {
            return false;
        }
        Elo atual = linhas[linha];
        while (atual != null) {
            if (atual.coluna == coluna && atual.valor == valor) {
                return true;
            }
            atual = atual.prox;
        }
        return false;
    }

    //13 - Somar duas matrizes esparsas;
    public MatrizEsparsaLinkedList soma(MatrizEsparsaLinkedList outra) {
        int numLinhas = this.linhas.length;
        MatrizEsparsaLinkedList resultado = new MatrizEsparsaLinkedList(numLinhas);

        for (int i = 0; i < numLinhas; i++) {
            Elo atualA = this.linhas[i];
            Elo atualB = outra.linhas[i];

            while (atualA != null || atualB != null) {
                if (atualA != null && (atualB == null || atualA.coluna < atualB.coluna)) {
                    resultado.insertElement(i, atualA.coluna, atualA.valor);
                    atualA = atualA.prox;
                } else if (atualB != null && (atualA == null || atualB.coluna < atualA.coluna)) {
                    resultado.insertElement(i, atualB.coluna, atualB.valor);
                    atualB = atualB.prox;
                } else {//mesma coluna
                    int somaValor = atualA.valor + atualB.valor;
                    if (somaValor != 0) {
                        resultado.insertElement(i, atualA.coluna, somaValor);
                    }
                    atualA = atualA.prox;
                    atualB = atualB.prox;
                }
            }
        }
        return resultado;
    }
}
