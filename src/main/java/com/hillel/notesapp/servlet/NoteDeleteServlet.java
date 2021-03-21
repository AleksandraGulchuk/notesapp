package com.hillel.notesapp.servlet;

import com.hillel.notesapp.NotesRepository;
import com.hillel.notesapp.database.Service;
import com.hillel.notesapp.util.Checker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoteDeleteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr.length() == 0) {
            request.setAttribute("message", "Note not deleted: note not found");
            request.getRequestDispatcher("WEB-INF/views/resultmessage.jsp").forward(request, response);
            return;
        }
        Service service = NotesRepository.instance().getService();
        Checker checker = new Checker(service);
        int id = Integer.parseInt(idStr);
        if (checker.checkId(id)) {
            service.delete(id);
            request.setAttribute("message", "Note deleted successfully");
        } else {
            request.setAttribute("message", "Note not deleted: note not found");
        }
        request.getRequestDispatcher("WEB-INF/views/resultmessage.jsp").forward(request, response);
    }
}
