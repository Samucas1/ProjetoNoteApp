package com.unoteapp;


import com.unanoteapp.model.Note;
import com.unanoteapp.repository.NoteRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class NoteAppGUI {
    private JFrame frame;
    private JTextField titleField;
    private JTextArea contentArea;
    private JButton addButton, deleteButton;
    private JTable noteTable;
    private DefaultTableModel tableModel;
    private NoteRepository repository = new NoteRepository();

    public NoteAppGUI() {
        frame = new JFrame("Note App");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Título:");
        titleLabel.setBounds(10, 10, 80, 25);
        frame.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 10, 480, 25);
        frame.add(titleField);

        JLabel contentLabel = new JLabel("Conteúdo:");
        contentLabel.setBounds(10, 45, 80, 25);
        frame.add(contentLabel);

        contentArea = new JTextArea();
        contentArea.setBounds(100, 45, 480, 80);
        frame.add(contentArea);

        addButton = new JButton("Adicionar Nota");
        addButton.setBounds(100, 135, 150, 30);
        frame.add(addButton);

        deleteButton = new JButton("Excluir Nota");
        deleteButton.setBounds(260, 135, 150, 30);
        frame.add(deleteButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Título", "Conteúdo"}, 0);
        noteTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(noteTable);
        scrollPane.setBounds(10, 180, 570, 160);
        frame.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    Note note = new Note(0, title, content);
                    repository.addNote(note);
                    updateTable();
                    titleField.setText("");
                    contentArea.setText("");
                    JOptionPane.showMessageDialog(frame, "Nota adicionada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha título e conteúdo.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = noteTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    repository.deleteNote(id);
                    updateTable();
                    JOptionPane.showMessageDialog(frame, "Nota excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma nota para excluir.");
                }
            }
        });

        updateTable();
        frame.setVisible(true);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            tableModel.addRow(new Object[]{note.getId(), note.getTitle(), note.getContent()});
        }
    }

    public static void main(String[] args) {
        new NoteAppGUI();
    }
}
