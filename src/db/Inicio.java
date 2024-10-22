/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author 6003262
 */
public class Inicio {

   
    // Método para conectar a la base de datos
    public static Connection connect() {
        Connection conn = null;
        try {
            // Ruta del archivo de la base de datos
            String url = "jdbc:sqlite:base_datos.db";
            
            // Conexión a la base de datos
            conn = DriverManager.getConnection(url);
            
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
     // Método para crear una tabla
    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    age integer\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Crear la tabla
            stmt.execute(sql);
            System.out.println("Tabla creada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insert(String name, int age) {
    String sql = "INSERT INTO students(name, age) VALUES(?,?)";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.executeUpdate();
        System.out.println("Registro insertado.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    
    
    public static void main(String[] args) {
        // TODO code application logic here
        createNewTable();
        insert("pepe",7);
         insert("juan",27);
          insert("lolo",35);
    }
    
}
