package ContadorNosFila;

import java.util.LinkedList;
import java.util.Queue;

class Arvore {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = inserirRec(atual.esquerda, valor);
        } else {
            atual.direita = inserirRec(atual.direita, valor);
        }

        return atual;
    }

    public int contarNosComFila() {
        if (raiz == null) return 0;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        int contador = 0;

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            contador++;

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }

        return contador;
    }
}

