package AVL;

public class No {
    int chave, altura;
    No esquerdo, direito;

    public No (int chave) {
        this.chave = chave;
        this.altura = 1; // altura inicial do nó novo é 1
    }
}
