package ARB;

public class Main {
    public static void main(String[] args) {
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();

        int[] chaves = {10, 20, 30, 15, 5, 25};
        for (int chave : chaves) {
            arvore.inserirValor(chave);
        }

        System.out.println("Árvore após inserções (in-order):");
        arvore.imprimirEmOrdem();

        arvore.deletarValor(15);
        arvore.deletarValor(10);

        System.out.println("Árvore após remoções (in-order):");
        arvore.imprimirEmOrdem();
    }
}
