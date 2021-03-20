package com.hillel.notesapp.database;

import com.hillel.notesapp.dto.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ListService implements Service {

    List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> getAllNotes() {
        return notes;
    }

    @Override
    public void delete(int index) {
        int delIndex = -1;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == index) {
                delIndex = i;
                break;
            }
        }
        if (delIndex >= 0)
            notes.remove(delIndex);
    }

    @Override
    public void add(String title, String description) {
        notes.add(new Note()
                .setId(newId())
                .setTitle(title)
                .setDescription(description)
                .setDateTime(LocalDateTime.now()));
    }

    private int newId() {
        return notes.stream().map(Note::getId)
                .max(Comparator.comparingInt(a -> a))
                .map(id -> id + 1).orElse(1);
    }

}
