package com.hillel.notesapp.database;

import com.hillel.notesapp.dto.Note;

import java.util.List;

public interface Service {

    List<Note> getAllNotes();

    void delete(int index);

    void add(Note note);

    Note getNote(int id);
}
