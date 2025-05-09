package com.unanoteapp.repository;

import com.unanoteapp.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteRepositoryTest {

    private NoteRepository repository;

    @BeforeEach
    void setUp() {
        repository = new NoteRepository();
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
        repository.addNote("Nota 2", "Texto da nota 2");
        Note note = repository.getNoteById(1);
        assertNotNull(note);
        assertEquals("Nota 2", note.getTitle());
    }

    @Test
    void testUpdateNote () {
        repository.addNote("Original", "Texto");
        boolean updated = repository.updateNote(1, "Atualizado", "Novo conteúdo");
        Note updatedNote = repository.getNoteById(1);

        assertTrue(updated);
        assertEquals("Atualizado", updatedNote.getTitle());
    }

    @Test
    void testDeleteNote() {
        repository.addNote("Nota Excluída", "Conteúdo");
        boolean deleted = repository.deleteNote(1);
        Note note = repository.getNoteById(1);

        assertTrue(deleted);
        assertNull(note);
    }

    @Test
    void testGetNoteByInvalidIdReturnsNull() {
        Note note = repository.getNoteById(999);
        assertNull(note);
    }
}