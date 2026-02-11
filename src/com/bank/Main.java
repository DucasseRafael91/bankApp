package com.bank;

import com.bank.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {

  public static void main(String[] args) {

    try {
      // Récupération de l'instance
      DatabaseConnection db = DatabaseConnection.getInstance();
      Connection conn = db.getConnection();

      // Vérification simple
      if (conn != null && !conn.isClosed()) {
        System.out.println("Connexion établie avec succès !");
      }

      // Test avec une requête
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT 1");

      if (rs.next()) {
        System.out.println("Test SQL réussi !");
      }

    } catch (SQLException e) {
      System.out.println("Erreur de connexion !");
      e.printStackTrace();
    }
  }
}
