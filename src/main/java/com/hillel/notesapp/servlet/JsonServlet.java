package com.hillel.notesapp.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected void writeJson(Object object, HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setHeader("Content-Type", "application/json");
            String strResponse = objectMapper.writeValueAsString(object);
            httpServletResponse.getWriter().write(strResponse);
            httpServletResponse.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> T readJson(Class<T> clazz, HttpServletRequest httpServletRequest) {
        try {
            String requestString = new String(httpServletRequest.getInputStream().readAllBytes());
            return objectMapper.readValue(requestString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
