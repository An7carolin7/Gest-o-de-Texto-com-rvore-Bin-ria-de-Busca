import java.io.*;
import java.text.Normalizer;

public class ProcessadorTexto {
    public static void processarArquivo(String nomeArquivo, ArvoreBinariaBusca arvore) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
        String linha;
        
        while ((linha = leitor.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                arvore.incrementarTotalLinhas();
                processarLinha(linha, arvore);
            }
        }
        
        leitor.close();
    }
    
    private static void processarLinha(String linha, ArvoreBinariaBusca arvore) {
        // Remover acentos e caracteres especiais
        linha = Normalizer.normalize(linha, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        
        // Remover pontuações e converter para minúsculas
        linha = linha.replaceAll("[^a-zA-Z0-9\\s-]", "").toLowerCase();
        
        // Substituir números por extenso (simplificado)
        linha = linha.replaceAll("\\b2\\b", "dois");
        
        // Dividir palavras (considerando hífens como separadores)
        String[] palavras = linha.split("[\\s-]+");
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                arvore.incrementarTotalPalavras();
                Palavra existente = arvore.buscar(palavra);
                
                if (existente != null) {
                    existente.incrementarOcorrencias();
                } else {
                    arvore.inserir(new Palavra(palavra));
                }
            }
        }
    }
}