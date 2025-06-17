package ARB;

public class NoRB {
    public int chave;
    public CorDoNo cor;
    public NoRB esquerda;
    public NoRB direita;
    public NoRB pai;

    public NoRB(int chave) {
        this.chave = chave;
        this.cor = CorDoNo.VERMELHO;
    }
}
