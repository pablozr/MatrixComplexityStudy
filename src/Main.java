public class Main {
    public static void main(String[] args) {
        MatrizEsparsaEstatica matriz = new MatrizEsparsaEstatica(3, 3);

        // Inserir elementos
        matriz.insertElement(0,0, 1);
        matriz.insertElement(0,1, 2);
        matriz.insertElement(0,2, 3);
        matriz.insertElement(1,0, 4);
        matriz.insertElement(1,1, 5);
        matriz.insertElement(1,2, 6);
        matriz.insertElement(2,0, 7);
        matriz.insertElement(2,1, 8);
        matriz.insertElement(2,2, 9);

        // Imprimir matriz
        matriz.printMatriz();

        System.out.println("--------------------------------------------------------------------");

        //Remover um elemento
        //matriz.removeElement(1,2);

        //Impressão de matriz após remoção do 6
        matriz.printMatriz();

        System.out.println("--------------------------------------------------------------------");

        //Busca por um elemento
        matriz.searchElement(4);

        //Imprimir uma matriz vazia
        //matriz.printMatrizVazia();

        //transposta
        //matriz.obterTransposta().printMatriz();

        //simetrica
        System.out.println(matriz.isSimetrica());

        //triangular superior
        matriz.isTriangularSuperior();
    }
}