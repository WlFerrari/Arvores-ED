import java.util.LinkedList;
import java.util.Queue;

public class ContadorFolhas {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.raiz = new No("A");
        arvore.raiz.esquerda = new No("B");
        arvore.raiz.direita = new No("C");
        arvore.raiz.esquerda.esquerda = new No("D");
        arvore.raiz.direita.direita = new No("E");

        int folhas = contarFolhas(arvore.raiz);
        System.out.println("Número de folhas: " + folhas);
    }

    static class No {
        String item;
        No esquerda, direita;

        public No(String item) {
            this.item = item;
            esquerda = direita = null;
        }
    }

    static class Arvore {
        No raiz;
    }

    public static int contarFolhas(No raiz){
        if (raiz == null) {
            return 0;
        }

        int contador = 0;
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()){
            No atual = fila.poll();

            if (atual.esquerda == null && atual.direita == null){
                contador++;
            }
            if (atual.esquerda != null) {
                fila.add(atual.esquerda);
            }
            if (atual.direita != null) {
                fila.add(atual.direita);
            }
        }

        return contador;
    }
}
