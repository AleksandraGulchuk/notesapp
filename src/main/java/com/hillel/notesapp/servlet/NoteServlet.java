package com.hillel.notesapp.servlet;


import com.hillel.notesapp.NotesRepository;
import com.hillel.notesapp.dto.Note;
import com.hillel.notesapp.dto.NoteResponse;
import com.hillel.notesapp.dto.NoteStringParam;
import com.hillel.notesapp.util.NoteSerializer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


public class NoteServlet extends JsonServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Note> notes = NotesRepository.instance()
                .getService()
                .getAllNotes();
        List<NoteStringParam> noteStringParams = new ArrayList<>(notes.size());
        for (Note note : notes) {
            noteStringParams.add(NoteSerializer.convertNoteToStringParam(note));
        }
        NoteResponse noteResponse = new NoteResponse()
                .setNotes(noteStringParams)
                .setStatus("ok");
        writeJson(noteResponse, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        NoteStringParam note = readJson(NoteStringParam.class, request);
        NotesRepository.instance()
                .getService()
                .add(note.getTitle(), note.getDescription());
        NoteResponse noteResponse = new NoteResponse()
                .setStatus("ok")
                .setMessage("Note added successfully");
        writeJson(noteResponse, response);
    }

}
