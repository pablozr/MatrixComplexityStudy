import java.util.Random;

<<<<<<< Updated upstream
=======
public class MatrizEsparsaLinkedList implements GeradorMatriz {
    private Elo[] linhas;
    private static final double esparsidade = 0.6;

>>>>>>> Stashed changes
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

    //14 - Multiplicar duas matrizes esparsas
    public MatrizEsparsaLinkedList multiplica(MatrizEsparsaLinkedList outra) {
        int numLinhas = this.linhas.length;
        int numColunas = outra.linhas.length;
        MatrizEsparsaLinkedList resultado = new MatrizEsparsaLinkedList(numLinhas);

        for (int i = 0; i < numLinhas; i++) {
            Elo linhaAtual = this.linhas[i];
            while (linhaAtual != null) {
                int colunaAtual = linhaAtual.coluna;
                Elo colunaOutra = outra.linhas[colunaAtual];
                while (colunaOutra != null) {
                    int colunaResultado = colunaOutra.coluna;
                    int valorMultiplicado = linhaAtual.valor * colunaOutra.valor;

                    int valorExistente = resultado.searchElement(i, colunaResultado);
                    resultado.insertElement(i, colunaResultado, valorExistente + valorMultiplicado);

                    colunaOutra = colunaOutra.prox;
                }
                linhaAtual = linhaAtual.prox;
            }
        }
        return resultado;
    }

    //15 - Obter a matriz transposta
    public MatrizEsparsaLinkedList transposta() {
        int numLinhas = linhas.length;
        MatrizEsparsaLinkedList transposta = new MatrizEsparsaLinkedList(numLinhas);

        for (int i = 0; i < numLinhas; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                // Inverte a linha e a coluna ao inserir na matriz transposta
                transposta.insertElement(atual.coluna, i, atual.valor);
                atual = atual.prox;
            }
        }
        return transposta;
    }

    @Override
    public void gerarMatriz(int tamanho){
        final int TAMANHO_BLOCO = 100;
        Random random = new Random();
        int elementosNaoNulos = (int) (tamanho * tamanho * (1 - esparsidade));

        for (int blocoLinha = 0; blocoLinha < tamanho; blocoLinha += TAMANHO_BLOCO) {
            for (int blocoColuna = 0; blocoColuna < tamanho; blocoColuna += TAMANHO_BLOCO) {
                int fimLinha = Math.min(blocoLinha + TAMANHO_BLOCO, tamanho);
                int fimColuna = Math.min(blocoColuna + TAMANHO_BLOCO, tamanho);

                int linhasBloco = fimLinha - blocoLinha;
                int colunasBloco = fimColuna - blocoColuna;
                int areaBloco = linhasBloco * colunasBloco;

                int elementosNoBloco = (int) Math.round((double) areaBloco / (tamanho * tamanho) * elementosNaoNulos);

                while (elementosNoBloco > 0) {
                    int i = random.nextInt(fimLinha - blocoLinha) + blocoLinha;
                    int j = random.nextInt(fimColuna - blocoColuna) + blocoColuna;

                    if (searchElement(i, j) == 0) {
                        int valor = random.nextInt(100) + 1;
                        insertElement(i, j, valor);
                        elementosNoBloco--;
                    }
                }
            }
        }
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

    //6
    public boolean isVazia() {
        for (Elo linha : linhas) {
            if (linha != null) {
                return false;
            }
        }
        return true;
    }
    //7
    public boolean isDiagonal() {
        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                if (atual.coluna != i) {
                    return false;
                }
                atual = atual.prox;
            }
        }
        return true;
    }

    // 8
    public boolean isLinha() {
        int linhaComElementos = -1;

        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];

            while (atual != null) {
                if (linhaComElementos != -1 && linhaComElementos != i) {
                    return false;
                }

                linhaComElementos = i;

                atual = atual.prox;
            }
        }

        return linhaComElementos != -1;
    }

    //9
    public boolean isColuna() {
        int colunaComElementos = -1;

        for (Elo linha : linhas) {
            Elo atual = linha;

            while (atual != null) {
                if (colunaComElementos != -1 && colunaComElementos != atual.coluna) {
                    return false;
                }

                colunaComElementos = atual.coluna;

                atual = atual.prox;
            }
        }

        return colunaComElementos != -1;
    }

    //10
    public boolean isTriangularInferior() {
        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                if (atual.coluna > i) {
                    return false;
                }
                atual = atual.prox;
            }
        }
        return true;
    }

    //11 - Verificar se é uma matriz triangular superior (só tem elementos da diagonal principal para cima);
    public boolean isTriangularSuperior() {
        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                if (atual.coluna < i) {
                    return false;
                }
                atual = atual.prox;
            }
        }
        return true;
    }

    //12 - Verificar se a matriz é simétrica; (A[i,j] = A[j,i] para todo i, j)
    public boolean isSimetrica() {
        for (int i = 0; i < linhas.length; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                int j = atual.coluna;
                int valor = atual.valor;

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
    public MatrizEsparsaLinkedList somarMatrizes(MatrizEsparsaLinkedList outra) {
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

    //14 - Multiplicar duas matrizes esparsas
    public MatrizEsparsaLinkedList multiplicaMatrizes(MatrizEsparsaLinkedList outra) {
        int numLinhas = this.linhas.length;
        int numColunas = outra.linhas.length;
        MatrizEsparsaLinkedList resultado = new MatrizEsparsaLinkedList(numLinhas);

        for (int i = 0; i < numLinhas; i++) {
            Elo linhaAtual = this.linhas[i];
            while (linhaAtual != null) {
                int colunaAtual = linhaAtual.coluna;
                Elo colunaOutra = outra.linhas[colunaAtual];
                while (colunaOutra != null) {
                    int colunaResultado = colunaOutra.coluna;
                    int valorMultiplicado = linhaAtual.valor * colunaOutra.valor;

                    int valorExistente = resultado.searchElement(i, colunaResultado);
                    resultado.insertElement(i, colunaResultado, valorExistente + valorMultiplicado);

                    colunaOutra = colunaOutra.prox;
                }
                linhaAtual = linhaAtual.prox;
            }
        }
        return resultado;
    }

    //15 - Obter a matriz transposta
    public MatrizEsparsaLinkedList obterTransposta() {
        int numLinhas = linhas.length;
        MatrizEsparsaLinkedList transposta = new MatrizEsparsaLinkedList(numLinhas);

        for (int i = 0; i < numLinhas; i++) {
            Elo atual = linhas[i];
            while (atual != null) {
                transposta.insertElement(atual.coluna, i, atual.valor);
                atual = atual.prox;
            }
        }
        return transposta;
    }

}
