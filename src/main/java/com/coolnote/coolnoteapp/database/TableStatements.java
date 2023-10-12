package com.coolnote.coolnoteapp.database;

public interface TableStatements {
    String NOTES = "CREATE TABLE IF NOT EXISTS notes (" +
            "id SERIAL PRIMARY KEY," +
            "description TEXT" +
            ");";
}
