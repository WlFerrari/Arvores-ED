public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        arvore.inserir("M");
        arvore.inserir("C");
        arvore.inserir("T");
        arvore.inserir("A");
        arvore.inserir("E");
        arvore.inserir("R");
        arvore.inserir("Z");

        arvore.imprimir();

        arvore.remover("C");

        arvore.imprimir();
    }
}
