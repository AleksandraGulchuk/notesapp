package com.hillel.notesapp.util;

import com.hillel.notesapp.dto.Note;
import com.hillel.notesapp.dto.NoteStringParam;

import java.time.LocalDateTime;

public class NoteSerializer {

    public static NoteStringParam convertNoteToStringParam(Note note) {
        return new NoteStringParam()
                .setId(Integer.toString(note.getId()))
                .setTitle(note.getTitle())
                .setDescription(note.getDescription())
                .setDateTime(note.getDateTime().toString());
    }

    public static Note convertNoteStringParamToNote(NoteStringParam note) {
        return new Note()
                .setId(Integer.parseInt(note.getId()))
                .setTitle(note.getTitle())
                .setDescription(note.getDescription())
                .setDateTime(LocalDateTime.parse(note.getDateTime()));
    }
}
