import java.util.Stack;

public class ArvoreIterativa {

    static class No {
        String valor;
        No esquerda, direita;

        public No(String valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }

    static class ArvoreBinaria {
        No raiz;

        public void emOrdemIterativo() {
            Stack<No> pilha = new Stack<>();
            No atual = raiz;

            while (atual != null || !pilha.isEmpty()) {
                while (atual != null) {
                    pilha.push(atual);
                    atual = atual.esquerda;
                }

                atual = pilha.pop();
                System.out.print(atual.valor + " ");

                atual = atual.direita;
            }
        }

        public void preOrdemIterativo() {
            if (raiz == null) return;

            Stack<No> pilha = new Stack<>();
            pilha.push(raiz);

            while (!pilha.isEmpty()) {
                No atual = pilha.pop();
                System.out.print(atual.valor + " ");

                if (atual.direita != null) {
                    pilha.push(atual.direita);
                }

                if (atual.esquerda != null) {
                    pilha.push(atual.esquerda);
                }
            }
        }

        public void posOrdemIterarivo () {
            if (raiz == null) return;

            Stack<No> pilha = new Stack<>();
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.raiz = new No("A");
        arvore.raiz.esquerda = new No("B");
        arvore.raiz.direita = new No("C");
        arvore.raiz.esquerda.esquerda = new No("D");
        arvore.raiz.esquerda.direita = new No("E");
        arvore.raiz.direita.direita = new No("F");

        System.out.println("Travessia em ordem (iterativa):");
        arvore.emOrdemIterativo();

        System.out.println("\nTravessia pré-ordem (iterativa):");
        arvore.preOrdemIterativo();
    }
}
