public class Main {
    public static void main(String[] args) {

    }

    static class No {
        String item;
        No esquerda, direita;
        int node;

        public No(String item) {
            this.item = item;
            esquerda = direita = null;
        }
    }

    static class Arvore {
        No raiz;
    }

    public static int contarNos(No node) {
        if (node == null) {
            return 0;
        }
        return 1 + contarNos(node.esquerda) + contarNos(node.direita);
    }

    public static void preOrdem(No no) {
        if (no != null) {
            System.out.println(no.item + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    public static void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.println(no.item + " ");
            emOrdem(no.direita);
        }
    }
}