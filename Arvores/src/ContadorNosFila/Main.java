package ContadorNosFila;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);

        int totalNos = arvore.contarNosComFila();
        System.out.println("Total de nós na árvore: " + totalNos);
    }
}