package ARB;

import static ARB.CorDoNo.PRETO;
import static ARB.CorDoNo.VERMELHO;

public class ArvoreRubroNegra {
    public NoRB raiz;

    public void inserirValor(int chave) {
        NoRB novoNo = new NoRB(chave);
        inserirNo(novoNo);
    }

    private void inserirNo(NoRB novoNo) {
        NoRB noAtual = raiz;
        NoRB pai = null;

        while (noAtual != null) {
            pai = noAtual;
            if (novoNo.chave < noAtual.chave) {
                noAtual = noAtual.esquerda;
            } else {
                noAtual = noAtual.direita;
            }
        }

        novoNo.pai = pai;

        if (pai == null) {
            raiz = novoNo;
        } else if (novoNo.chave < pai.chave) {
            pai.esquerda = novoNo;
        } else {
            pai.direita = novoNo;
        }

        novoNo.cor = VERMELHO;
        corrigirInsercao(novoNo);
    }

    private void corrigirInsercao(NoRB no) {
        while (no != raiz && no.pai.cor == VERMELHO) {
            if (no.pai == no.pai.pai.esquerda) {
                NoRB tio = no.pai.pai.direita;
                if (tio != null && tio.cor == VERMELHO) {
                    no.pai.cor = PRETO;
                    tio.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.direita) {
                        no = no.pai;
                        rotacaoEsquerda(no);
                    }
                    no.pai.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    rotacaoDireita(no.pai.pai);
                }
            } else {
                NoRB tio = no.pai.pai.esquerda;
                if (tio != null && tio.cor == VERMELHO) {
                    no.pai.cor = PRETO;
                    tio.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.esquerda) {
                        no = no.pai;
                        rotacaoDireita(no);
                    }
                    no.pai.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    rotacaoEsquerda(no.pai.pai);
                }
            }
        }
        raiz.cor = PRETO;
    }

    private void rotacaoEsquerda(NoRB x) {
        NoRB y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != null) y.esquerda.pai = x;
        y.pai = x.pai;
        if (x.pai == null) raiz = y;
        else if (x == x.pai.esquerda) x.pai.esquerda = y;
        else x.pai.direita = y;
        y.esquerda = x;
        x.pai = y;
    }

    private void rotacaoDireita(NoRB y) {
        NoRB x = y.esquerda;
        y.esquerda = x.direita;
        if (x.direita != null) x.direita.pai = y;
        x.pai = y.pai;
        if (y.pai == null) raiz = x;
        else if (y == y.pai.direita) y.pai.direita = x;
        else y.pai.esquerda = x;
        x.direita = y;
        y.pai = x;
    }

    private NoRB buscarNo(int chave) {
        NoRB atual = raiz;
        while (atual != null && chave != atual.chave) {
            if (chave < atual.chave) atual = atual.esquerda;
            else atual = atual.direita;
        }
        return atual;
    }

    private void transplantar(NoRB u, NoRB v) {
        if (u.pai == null) raiz = v;
        else if (u == u.pai.esquerda) u.pai.esquerda = v;
        else u.pai.direita = v;
        if (v != null) v.pai = u.pai;
    }

    public void deletarValor(int chave) {
        NoRB noParaDeletar = buscarNo(chave);
        if (noParaDeletar == null) return;

        NoRB y = noParaDeletar;
        CorDoNo corOriginalDeY = y.cor;
        NoRB x;

        if (noParaDeletar.esquerda == null) {
            x = noParaDeletar.direita;
            transplantar(noParaDeletar, noParaDeletar.direita);
        } else if (noParaDeletar.direita == null) {
            x = noParaDeletar.esquerda;
            transplantar(noParaDeletar, noParaDeletar.esquerda);
        } else {
            y = encontrarMinimo(noParaDeletar.direita);
            corOriginalDeY = y.cor;
            x = y.direita;
            if (y.pai == noParaDeletar) {
                if (x != null) x.pai = y;
            } else {
                transplantar(y, y.direita);
                y.direita = noParaDeletar.direita;
                y.direita.pai = y;
            }
            transplantar(noParaDeletar, y);
            y.esquerda = noParaDeletar.esquerda;
            y.esquerda.pai = y;
            y.cor = noParaDeletar.cor;
        }

        if (corOriginalDeY == PRETO && x != null) corrigirDelete(x);
    }

    private void corrigirDelete(NoRB x) {
        while (x != raiz && x.cor == PRETO) {
            if (x == x.pai.esquerda) {
                NoRB w = x.pai.direita;
                if (w.cor == VERMELHO) {
                    w.cor = PRETO;
                    x.pai.cor = VERMELHO;
                    rotacaoEsquerda(x.pai);
                    w = x.pai.direita;
                }
                if (corDe(w.esquerda) == PRETO && corDe(w.direita) == PRETO) {
                    w.cor = VERMELHO;
                    x = x.pai;
                } else {
                    if (corDe(w.direita) == PRETO) {
                        if (w.esquerda != null) w.esquerda.cor = PRETO;
                        w.cor = VERMELHO;
                        rotacaoDireita(w);
                        w = x.pai.direita;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = PRETO;
                    if (w.direita != null) w.direita.cor = PRETO;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                NoRB w = x.pai.esquerda;
                if (w.cor == VERMELHO) {
                    w.cor = PRETO;
                    x.pai.cor = VERMELHO;
                    rotacaoDireita(x.pai);
                    w = x.pai.esquerda;
                }
                if (corDe(w.direita) == PRETO && corDe(w.esquerda) == PRETO) {
                    w.cor = VERMELHO;
                    x = x.pai;
                } else {
                    if (corDe(w.esquerda) == PRETO) {
                        if (w.direita != null) w.direita.cor = PRETO;
                        w.cor = VERMELHO;
                        rotacaoEsquerda(w);
                        w = x.pai.esquerda;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = PRETO;
                    if (w.esquerda != null) w.esquerda.cor = PRETO;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }
        if (x != null) x.cor = PRETO;
    }

    private NoRB encontrarMinimo(NoRB no) {
        while (no.esquerda != null) no = no.esquerda;
        return no;
    }

    private CorDoNo corDe(NoRB no) {
        return no == null ? PRETO : no.cor;
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdemRec(raiz);
        System.out.println();
    }

    private void imprimirEmOrdemRec(NoRB no) {
        if (no != null) {
            imprimirEmOrdemRec(no.esquerda);
            String cor = (no.cor == VERMELHO) ? "V" : "P";
            System.out.print(no.chave + cor + " ");
            imprimirEmOrdemRec(no.direita);
        }
    }
}
