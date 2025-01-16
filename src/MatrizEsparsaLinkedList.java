public class MatrizEsparsaLinkedList {
    private Elo[] linhas;

    private static class Elo {
        int coluna;
        int valor;
        Elo prox;

        public Elo(int coluna, int valor) {
            this.coluna = coluna;
            this.valor = valor;
            this.prox = null;
        }
    }

    public MatrizEsparsaLinkedList(int numLinhas) {
        linhas = new Elo[numLinhas];
    }

}
