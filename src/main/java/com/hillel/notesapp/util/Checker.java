package com.hillel.notesapp.util;

import com.hillel.notesapp.database.Service;
import com.hillel.notesapp.dto.Note;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Checker {

    private final Service service;

    public boolean checkId(int id){
        List<Note> notes = service.getAllNotes();
        List<Integer> result = notes.stream()
                .map(Note::getId)
                .filter(noteId -> noteId.equals(id))
                .collect(Collectors.toList());
        return !result.isEmpty();
    }
}
