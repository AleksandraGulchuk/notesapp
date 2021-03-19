package com.hillel.notesapp;

import com.hillel.notesapp.database.DatabaseService;
import com.hillel.notesapp.database.Service;

public class NotesRepository {

    private static final NotesRepository INSTANCE = new NotesRepository();

    private final Service service;

    private NotesRepository() {
        this.service = new DatabaseService();
    }

    public static NotesRepository instance() {
        return INSTANCE;
    }

    public Service getService() {
        return service;
    }
}
