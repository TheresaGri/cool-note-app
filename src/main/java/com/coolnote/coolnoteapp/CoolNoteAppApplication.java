package com.coolnote.coolnoteapp;

import com.coolnote.coolnoteapp.database.Database;
import com.coolnote.coolnoteapp.database.TableInitializer;
import com.coolnote.coolnoteapp.database.TableStatements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class CoolNoteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolNoteAppApplication.class, args);

		Database database = new Database(
				"jdbc:postgresql://localhost:5432/notes",
				"theresa",
				"mysqlpassword");
		Map<String, String> tables = Map.of(
				"notes", TableStatements.NOTES
		);
		TableInitializer tableInitializer = new TableInitializer(database, tables);
		tableInitializer.initialize();
	}

}
