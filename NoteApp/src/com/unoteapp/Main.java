package com.unoteapp;

import com.unanoteapp.repository.NoteRepository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NoteRepository repo = new NoteRepository();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU DE ANOTAÇÕES ---");
            System.out.println("1. Criar nova anotação");
            System.out.println("2. Listar anotações");
            System.out.println("3. Editar anotação");
            System.out.println("4. Excluir anotação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    System.out.print("Conteúdo: ");
                    String content = scanner.nextLine();
                    repo.addNote(title, content);
                    System.out.println("Anotação criada!");
                }
                case 2 -> repo.getAllNotes().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID da anotação: ");
                    int idEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo título: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Novo conteúdo: ");
                    String newContent = scanner.nextLine();
                    if (repo.updateNote(idEdit, newTitle, newContent)) {
                        System.out.println("Anotação atualizada!");
                    } else {
                        System.out.println("Anotação não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da anotação: ");
                    int idDel = scanner.nextInt();
                    if (repo.deleteNote(idDel)) {
                        System.out.println("Anotação removida.");
                    } else {
                        System.out.println("Anotação não encontrada.");
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

