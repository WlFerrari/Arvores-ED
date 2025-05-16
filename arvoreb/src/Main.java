public class Main {
    public static void main(String[] args) {
        class No {
            String item;
            No esquerda, direita;

            public No (String item, String raiz){
                this.item = item;
                esquerda = direita = null;
            }
        }

        class Arvore {

            No raiz;

        }
    }
}
