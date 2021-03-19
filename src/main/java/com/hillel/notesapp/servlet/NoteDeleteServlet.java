package com.hillel.notesapp.servlet;

import com.hillel.notesapp.NotesRepository;
import com.hillel.notesapp.database.Service;
import com.hillel.notesapp.dto.NoteResponse;
import com.hillel.notesapp.dto.NoteStringParam;
import com.hillel.notesapp.util.Checker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoteDeleteServlet extends JsonServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        NoteStringParam noteStringParam = readJson(NoteStringParam.class, request);
        Service service = NotesRepository.instance().getService();
        Checker checker = new Checker(service);
        int id = Integer.parseInt(noteStringParam.getId());
        NoteResponse noteResponse = new NoteResponse();
        if (checker.checkId(id)) {
            service.delete(id);
            noteResponse.setStatus("ok")
                    .setMessage("Note deleted successfully");
        } else {
            noteResponse.setStatus("error")
                    .setMessage("Note not found");
        }
        writeJson(noteResponse, response);
    }

}
