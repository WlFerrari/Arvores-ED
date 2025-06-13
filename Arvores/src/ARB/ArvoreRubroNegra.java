package ARB;

public class ArvoreRubroNegra {
    public NoRB raiz;


    private void rotacaoDireita(NoRB y) {
        NoRB pai = y.pai;
        NoRB filhoEsquerda = y.esquerda;

        y.esquerda = filhoEsquerda.direita;
        if (filhoEsquerda.direita != null) {
            filhoEsquerda.direita.pai = y;
        }

        filhoEsquerda.direita = y;
        y.pai = filhoEsquerda;

        realocarPais(pai, y, filhoEsquerda);
    }

    private void rotacaoEsquerda(NoRB x) {
        NoRB pai = x.pai;
        NoRB filhoDireita = x.direita;
        if (filhoDireita == null) return;

        x.direita = filhoDireita.esquerda;
        if (filhoDireita.esquerda != null) {
            filhoDireita.esquerda.pai = x;
        }

        filhoDireita.esquerda = x;
        x.pai = filhoDireita;

        realocarPais(pai, x, filhoDireita);
    }

    private void realocarPais(NoRB pai, NoRB filhoAntigo, NoRB filhoNovo) {
        if (pai == null) {
            raiz = filhoNovo;
        } else if (pai.esquerda == filhoAntigo) {
            pai.esquerda = filhoNovo;
        } else if (pai.direita == filhoAntigo) {
            pai.direita = filhoNovo;
        } else {
            System.out.println("Nó não é um filho desse pai");
        }

        if (filhoNovo != null) {
            filhoNovo.pai = pai;
        }
    }

    public NoRB procurarNo(int chave) {
        NoRB noRB = raiz;
        while (noRB != null) {
            if (chave == noRB.chave) {
                return noRB;
            } else if (chave < noRB.chave) {
                noRB = noRB.esquerda;
            } else {
                noRB = noRB.direita;
            }
        }

        return null;
    }
}
