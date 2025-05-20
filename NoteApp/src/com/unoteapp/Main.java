package com.unoteapp;

import com.unanoteapp.repository.NoteRepository;

import api.QuoteService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	QuoteService quoteService = new QuoteService();
        String quote = quoteService.getRandomQuote();
        System.out.println("\n📜 Citação do dia: \"" + quote + "\"\n");

        NoteRepository repo1 = new NoteRepository();
        Scanner scanner1 = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU DE ANOTAÇÕES ---");
            System.out.println("1. Criar nova anotação");
            System.out.println("2. Listar anotações");
            System.out.println("3. Editar anotação");
            System.out.println("4. Excluir anotação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner1.nextInt();
            scanner1.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: ");
                    String title = scanner1.nextLine();
                    System.out.print("Conteúdo: ");
                    String content = scanner1.nextLine();
                    repo1.addNote(title, content);
                    System.out.println("Anotação criada!");
                }
                case 2 -> repo1.getAllNotes().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID da anotação: ");
                    int idEdit = scanner1.nextInt();
                    scanner1.nextLine();
                    System.out.print("Novo título: ");
                    String newTitle = scanner1.nextLine();
                    System.out.print("Novo conteúdo: ");
                    String newContent = scanner1.nextLine();
                    if (repo1.updateNote(idEdit, newTitle, newContent)) {
                        System.out.println("Anotação atualizada!");
                    } else {
                        System.out.println("Anotação não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da anotação: ");
                    int idDel = scanner1.nextInt();
                    if (repo1.deleteNote(idDel)) {
                        System.out.println("Anotação removida.");
                    } else {
                        System.out.println("Anotação não encontrada.");
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner1.close();
    }
}

