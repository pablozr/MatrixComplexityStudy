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
}
