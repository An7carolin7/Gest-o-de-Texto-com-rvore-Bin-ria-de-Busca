import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        // integrantes do grupo
        System.out.println("=== PROJETO 2 - ÁRVORE BINÁRIA DE BUSCA ===");
        System.out.println("===========Integrantes do grupo:===========");
        System.out.println("Ana Carolina Carvalho da Silva  RA:10425279  ");
        System.out.println("Beatriz Moreira Corazza         RA:10419580");
        System.out.println("Pedro Henrique de Sousa         RA:10369466");
        System.out.println("===========================================\n");
        
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); //buffer
            
            switch (opcao) {
                case 1:
                    carregarTexto(arvore, scanner);
                    break;
                case 2:
                    exibirEstatisticas(arvore);
                    break;
                case 3:
                    buscarPalavra(arvore, scanner);
                    break;
                case 4:
                    exibirTextoOrdenado(arvore);
                    break;
                case 5:
                    exibirPalavrasPorLetra(arvore, scanner);
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1- Carregar o texto");
        System.out.println("2- Estatísticas");
        System.out.println("3- Busca por palavra");
        System.out.println("4- Exibição do texto em ordem");
        System.out.println("5- Palavras por letra inicial");
        System.out.println("6- Encerrar");
    }
    
    private static void carregarTexto(ArvoreBinariaBusca arvore, Scanner scanner) {
        System.out.print("Digite o nome do arquivo com a letra da música (ex: musica.txt): ");
        String nomeArquivo = scanner.nextLine();
        
        try {
            ProcessadorTexto.processarArquivo(nomeArquivo, arvore);
            System.out.println("TEXTO CARREGADO COM SUCESSO!");
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
    
    private static void exibirEstatisticas(ArvoreBinariaBusca arvore) {
        if (arvore == null) {
            System.out.println("Por favor, carregue o texto primeiro (opção 1)");
            return;
        }
        arvore.exibirEstatisticas();
    }
    
    private static void buscarPalavra(ArvoreBinariaBusca arvore, Scanner scanner) {
        if (arvore == null) {
            System.out.println("Por favor, carregue o texto primeiro (opção 1)");
            return;
        }
        
        System.out.print("Digite a palavra a ser buscada: ");
        String palavra = scanner.nextLine().toLowerCase();
        
        Palavra resultado = arvore.buscar(palavra);
        
        if (resultado != null) {
            System.out.println("A palavra '" + palavra + "' aparece " + resultado.getOcorrencias() + " vezes no texto.");
        } else {
            System.out.println("A palavra '" + palavra + "' não foi encontrada no texto.");
        }
    }
    
    private static void exibirTextoOrdenado(ArvoreBinariaBusca arvore) {
        if (arvore == null) {
            System.out.println("Por favor, carregue o texto primeiro (opção 1)");
            return;
        }
        
        System.out.println("\n=== PALAVRAS DO TEXTO EM ORDEM ALFABÉTICA ===");
        arvore.emOrdem();
    }
    
    private static void exibirPalavrasPorLetra(ArvoreBinariaBusca arvore, Scanner scanner) {
        if (arvore == null) {
            System.out.println("Por favor, carregue o texto primeiro (opção 1)");
            return;
        }
        
        System.out.print("Digite a letra inicial para filtrar as palavras: ");
        char letra = scanner.nextLine().toLowerCase().charAt(0);
        arvore.exibirPalavrasPorLetra(letra);
    }
}