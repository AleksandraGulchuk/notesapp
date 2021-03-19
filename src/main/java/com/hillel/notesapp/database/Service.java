package com.hillel.notesapp.database;

import com.hillel.notesapp.dto.Note;

import java.util.List;

public interface Service {

    public List<Note> getAllNotes();
    public void delete(int index);
    public void add(String title, String description);

}
