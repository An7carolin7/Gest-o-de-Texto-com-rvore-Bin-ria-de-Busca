public class ArvoreBinariaBusca {
    private No raiz;
    private int totalPalavras;
    private int totalPalavrasDistintas;
    private int totalLinhas;
    private String palavraMaisLonga;

    private class No {
        Palavra palavra;
        No esquerda;
        No direita;

        public No(Palavra palavra) {
            this.palavra = palavra;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public ArvoreBinariaBusca() {
        raiz = null;
        totalPalavras = 0;
        totalPalavrasDistintas = 0;
        totalLinhas = 0;
        palavraMaisLonga = "";
    }

    public void inserir(Palavra palavra) {
        raiz = inserir(raiz, palavra);
    }

    private No inserir(No no, Palavra palavra) {
        if (no == null) {
            totalPalavrasDistintas++;
            if (palavra.getPalavra().length() > palavraMaisLonga.length()) {
                palavraMaisLonga = palavra.getPalavra();
            }
            return new No(palavra);
        }

        int comparacao = palavra.compareTo(no.palavra);

        if (comparacao < 0) {
            no.esquerda = inserir(no.esquerda, palavra);
        } else if (comparacao > 0) {
            no.direita = inserir(no.direita, palavra);
        } else {
            no.palavra.incrementarOcorrencias();
        }

        return no;
    }

    public Palavra buscar(String palavra) {
        return buscar(raiz, palavra.toLowerCase());
    }

    private Palavra buscar(No no, String palavra) {
        if (no == null) {
            return null;
        }

        int comparacao = palavra.compareToIgnoreCase(no.palavra.getPalavra());

        if (comparacao < 0) {
            return buscar(no.esquerda, palavra);
        } else if (comparacao > 0) {
            return buscar(no.direita, palavra);
        } else {
            return no.palavra;
        }
    }

    public void emOrdem() {
        emOrdem(raiz);
    }

    private void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.println(no.palavra);
            emOrdem(no.direita);
        }
    }

    public void incrementarTotalPalavras() {
        totalPalavras++;
    }

    public void incrementarTotalLinhas() {
        totalLinhas++;
    }

    public void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO TEXTO ===");
        System.out.println("Total de linhas: " + totalLinhas);
        System.out.println("Total de palavras: " + totalPalavras);
        System.out.println("Total de palavras distintas: " + totalPalavrasDistintas);
        System.out.println("Palavra mais longa: " + palavraMaisLonga + " (" + palavraMaisLonga.length() + " letras)");
    }

    // Função adicional: exibir palavras que começam com uma letra específica
    public void exibirPalavrasPorLetra(char letra) {
        System.out.println("\n=== PALAVRAS QUE COMEÇAM COM '" + letra + "' ===");
        exibirPalavrasPorLetra(raiz, Character.toLowerCase(letra));
        System.out.println("Total encontrado: " + contarPalavrasPorLetra(raiz, Character.toLowerCase(letra)));
    }

    private void exibirPalavrasPorLetra(No no, char letra) {
        if (no != null) {
            exibirPalavrasPorLetra(no.esquerda, letra);
            if (no.palavra.getPalavra().charAt(0) == letra) {
                System.out.println(no.palavra);
            }
            exibirPalavrasPorLetra(no.direita, letra);
        }
    }

    private int contarPalavrasPorLetra(No no, char letra) {
        if (no == null) {
            return 0;
        }
        
        int count = 0;
        if (no.palavra.getPalavra().charAt(0) == letra) {
            count = 1;
        }
        
        return count + contarPalavrasPorLetra(no.esquerda, letra) 
                   + contarPalavrasPorLetra(no.direita, letra);
    }
}