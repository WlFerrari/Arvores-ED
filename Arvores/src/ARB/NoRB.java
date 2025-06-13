package ARB;

public class NoRB{
    int chave;
    Cores cor;
    NoRB esquerda, direita;
    NoRB pai;

    public NoRB(int chave) {
        this.chave = chave;
        this.cor = Cores.VERMELHO; // inserções normalmente começam vermelhas
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
    }
}
