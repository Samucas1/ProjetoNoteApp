package com.unanoteapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	private static final String URL = "jdbc:sqlite:notes.db";
	
	public static Connection connect() {
        try {
        	return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
       }
    }

}
