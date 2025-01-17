public class Main {
    public static void main(String[] args) {
        //TESTES ESPARSA NORMAL

        /*MatrizEsparsaEstatica matriz = new MatrizEsparsaEstatica(3, 3);

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

        //transposta
        //matriz.obterTransposta().printMatriz();

        //simetrica
        System.out.println(matriz.isSimetrica());

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
    }
}