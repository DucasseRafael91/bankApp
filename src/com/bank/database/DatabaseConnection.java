package com.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static DatabaseConnection instance;
  private Connection connection;
  private String url;
  private String user;
  private String password;

  //Constructeur privé : personne ne peut faire "new DatabaseConnection()"
  private DatabaseConnection() {
    this.url = "jdbc:mysql://localhost:3306/bank";
    this.user = "root";
    this.password = "";
    try {
      this.connection = DriverManager.getConnection(url, user, password);

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  // point d'accès unique
  public static synchronized DatabaseConnection getInstance() {
    if (instance == null) {
      instance = new DatabaseConnection();
    }
    return instance;
  }

  public Connection getConnection() {
    return this.connection;
  }



}
