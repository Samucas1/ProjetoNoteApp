package com.unanoteapp.repository;

import com.unanoteapp.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteRepositoryTest {

    private NoteRepository repository;

    @BeforeEach
    void setUp() {
        repository = new NoteRepository();
        clearDatabase();
    }

    void clearDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM notes");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro ao limpar o banco de dados: " + e.getMessage());
        }
    }

    @Test
    void testAddNote() {
        repository.addNote("Nota 1", "Conteúdo da nota 1");
        List<Note> notes = repository.getAllNotes();
        assertEquals(1, notes.size());
        assertEquals("Nota 1", notes.get(0).getTitle());
    }

    @Test
    void testGetNoteById() {
        int noteId = repository.addNote("Nota 2", "Texto da nota 2");
        Note note = repository.getNoteById(noteId);
        assertNotNull(note);
        assertEquals("Nota 2", note.getTitle());
    }

    @Test
    void testUpdateNote() {
        int noteId = repository.addNote("Original", "Texto");
        boolean updated = repository.updateNote(noteId, "Atualizado", "Novo conteúdo");
        Note updatedNote = repository.getNoteById(noteId);

        assertTrue(updated);
        assertEquals("Atualizado", updatedNote.getTitle());
    }

    @Test
    void testDeleteNote() {
        int noteId = repository.addNote("Nota Excluída", "Conteúdo");
        boolean deleted = repository.deleteNote(noteId);
        Note note = repository.getNoteById(noteId);

        assertTrue(deleted);
        assertNull(note);
    }

    @Test
    void testGetNoteByInvalidIdReturnsNull() {
        Note note = repository.getNoteById(999);
        assertNull(note);
    }
}