package com.hillel.notesapp.servlet;


import com.hillel.notesapp.NotesRepository;
import com.hillel.notesapp.database.Service;
import com.hillel.notesapp.dto.Note;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NoteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Service service = NotesRepository.instance().getService();
        List<Note> notes = service.getAllNotes();
        String idStr = request.getParameter("id");
        if (idStr == null) {
            request.setAttribute("notes", notes);
            request.getRequestDispatcher("WEB-INF/views/main.jsp").forward(request, response);
        } else {
            int id = Integer.parseInt(idStr);
            for (Note note : notes) {
                if (id == note.getId()) {
                    request.setAttribute("note", note);
                    request.getRequestDispatcher("WEB-INF/views/note.jsp").forward(request, response);
                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        if (title.length() != 0 && description.length() != 0) {
            NotesRepository.instance()
                    .getService()
                    .add(new Note()
                            .setTitle(title)
                            .setDescription(description));
            request.setAttribute("message", "Note added successfully");
        } else {
            request.setAttribute("message", "Note not added: Note must contain title and description");
        }
        request.getRequestDispatcher("WEB-INF/views/resultmessage.jsp").forward(request, response);
    }
}
