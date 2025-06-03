import static java.lang.Math.max;

public class ArvoreAVL {

    private class No {
        String item;
        No esquerda, direita;
        int altura;

        public No(String item) {
            this.item = item;
            this.altura = 0;
        }
    }

    private No raiz;

    private int altura(No no) {
        return no != null ? no.altura : -1;
    }

    private void atualizarAltura(No no) {
        int alturaEsq = altura(no.esquerda);
        int alturaDir = altura(no.direita);
        no.altura = max(alturaEsq, alturaDir) + 1;
    }

    private int fatorBalanceamento(No no) {
        return altura(no.direita) - altura(no.esquerda);
    }

    private No rotacaoDireita(No no) {
        No filhoEsq = no.esquerda;
        no.esquerda = filhoEsq.direita;
        filhoEsq.direita = no;

        atualizarAltura(no);
        atualizarAltura(filhoEsq);

        return filhoEsq;
    }

    private No rotacaoEsquerda(No no) {
        No filhoDir = no.direita;
        no.direita = filhoDir.esquerda;
        filhoDir.esquerda = no;

        atualizarAltura(no);
        atualizarAltura(filhoDir);

        return filhoDir;
    }

    private No rebalancear(No no) {
        atualizarAltura(no);
        int fb = fatorBalanceamento(no);

        if (fb < -1) {
            if (fatorBalanceamento(no.esquerda) <= 0) {
                no = rotacaoDireita(no);
            } else {
                no.esquerda = rotacaoEsquerda(no.esquerda);
                no = rotacaoDireita(no);
            }
        } else if (fb > 1) {
            if (fatorBalanceamento(no.direita) >= 0) {
                no = rotacaoEsquerda(no);
            } else {
                no.direita = rotacaoDireita(no.direita);
                no = rotacaoEsquerda(no);
            }
        }

        return no;
    }

    // Os códigos abaixos feitos com base nos codigos anteriores no repositório e auxílio do ChatGPT
    private No inserir(No no, String item) {
        if (no == null) return new No(item);

        if (item.compareTo(no.item) < 0) {
            no.esquerda = inserir(no.esquerda, item);
        } else if (item.compareTo(no.item) > 0) {
            no.direita = inserir(no.direita, item);
        } else {
            return no;
        }

        return rebalancear(no);
    }

    public void inserir(String item) {
        raiz = inserir(raiz, item);
    }

    private No remover(No no, String item) {
        if (no == null) return null;

        if (item.compareTo(no.item) < 0) {
            no.esquerda = remover(no.esquerda, item);
        } else if (item.compareTo(no.item) > 0) {
            no.direita = remover(no.direita, item);
        } else {
            if (no.esquerda == null || no.direita == null) {
                no = (no.esquerda != null) ? no.esquerda : no.direita;
            } else {
                No sucessor = encontrarMinimo(no.direita);
                no.item = sucessor.item;
                no.direita = remover(no.direita, sucessor.item);
            }
        }

        if (no != null)
            no = rebalancear(no);
        return no;
    }

    public void remover(String item) {
        raiz = remover(raiz, item);
    }

    private No encontrarMinimo(No no) {
        while (no.esquerda != null)
            no = no.esquerda;
        return no;
    }

    private void imprimirEmOrdem(No no) {
        if (no != null) {
            imprimirEmOrdem(no.esquerda);
            System.out.println(no.item + " (Altura: " + no.altura + ")");
            imprimirEmOrdem(no.direita);
        }
    }

    public void imprimir() {
        imprimirEmOrdem(raiz);
    }
}
