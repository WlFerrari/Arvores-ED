package ArvoreIterativa;

import java.util.Stack;

class No {
    int valor;
    No esquerdo, direito;

    public No(int valor) {
        this.valor = valor;
        esquerdo = direito = null;
    }
}

public class ArvoreBinaria {
    No raiz;

    // Inserção Iterativa
    public void inserir(int valor) {
        No novoNo = new No(valor);

        if (raiz == null) {
            raiz = novoNo;
            return;
        }

        No atual = raiz;
        No pai = null;

        while (atual != null) {
            pai = atual;
            if (valor < atual.valor) {
                atual = atual.esquerdo;
            } else {
                atual = atual.direito;
            }
        }

        if (valor < pai.valor) {
            pai.esquerdo = novoNo;
        } else {
            pai.direito = novoNo;
        }
    }

    // Busca Iterativa
    public boolean buscar(int valor) {
        No atual = raiz;

        while (atual != null) {
            if (valor == atual.valor) {
                return true;
            } else if (valor < atual.valor) {
                atual = atual.esquerdo;
            } else {
                atual = atual.direito;
            }
        }

        return false;
    }

    // Em ordem iterativo (sem recursão)
    public void emOrdemIterativo() {
        Stack<No> pilha = new Stack<>();
        No atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerdo;
            }

            atual = pilha.pop();
            System.out.print(atual.valor + " ");
            atual = atual.direito;
        }
    }

    // Remoção Iterativa
    public void remover(int valor) {
        No atual = raiz;
        No pai = null;

        while (atual != null && atual.valor != valor) {
            pai = atual;
            if (valor < atual.valor) {
                atual = atual.esquerdo;
            } else {
                atual = atual.direito;
            }
        }

        if (atual == null) return; // Valor não encontrado

        // Caso 1: Nó com 0 ou 1 filho
        if (atual.esquerdo == null || atual.direito == null) {
            No novoFilho = (atual.esquerdo != null) ? atual.esquerdo : atual.direito;

            if (pai == null) {
                raiz = novoFilho; // Removendo a raiz
            } else if (pai.esquerdo == atual) {
                pai.esquerdo = novoFilho;
            } else {
                pai.direito = novoFilho;
            }
        }
        // Caso 2: Nó com dois filhos
        else {
            No sucessorPai = atual;
            No sucessor = atual.direito;

            while (sucessor.esquerdo != null) {
                sucessorPai = sucessor;
                sucessor = sucessor.esquerdo;
            }

            atual.valor = sucessor.valor;

            if (sucessorPai != atual) {
                sucessorPai.esquerdo = sucessor.direito;
            } else {
                sucessorPai.direito = sucessor.direito;
            }
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            arvore.inserir(v);
        }

        System.out.print("Em ordem (iterativo): ");
        arvore.emOrdemIterativo(); // 20 30 40 50 60 70 80
        System.out.println();

        System.out.println("Buscar 60: " + arvore.buscar(60)); // true
        System.out.println("Removendo 70...");
        arvore.remover(70);
        System.out.print("Nova árvore em ordem: ");
        arvore.emOrdemIterativo(); // 20 30 40 50 60 80
    }
}
