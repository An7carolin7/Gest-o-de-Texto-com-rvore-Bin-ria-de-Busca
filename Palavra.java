public class Palavra implements Comparable<Palavra> {
    private String palavra;
    private int ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra.toLowerCase();
        this.ocorrencias = 1;
    }

    public String getPalavra() {
        return palavra;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public void incrementarOcorrencias() {
        ocorrencias++;
    }

    @Override
    public int compareTo(Palavra outra) {
        return this.palavra.compareToIgnoreCase(outra.getPalavra());
    }

    @Override
    public String toString() {
        return palavra + " (" + ocorrencias + ")";
    }
}