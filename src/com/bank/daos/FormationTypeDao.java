package com.venteformation.daos;

import com.venteformation.entities.FormationType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dao FormationType.
 */
public class FormationTypeDao implements Dao<FormationType> {

  private static final String URL = "jdbc:mariadb://localhost:3307/formation";
  private static final String LOGIN = "root";
  private static final String PASSWORD = "";

  @Override
  public void create(FormationType formationType) {
    // À implémenter plus tard
  }

  @Override
  public void update(FormationType formationType) {
    // À implémenter plus tard
  }

  @Override
  public void delete(int id) {
    // À implémenter plus tard
  }

  @Override
  public FormationType findById(int id) {
    // À implémenter plus tard
    return null;
  }

  @Override
  public ArrayList<FormationType> findAll() {
    ArrayList<FormationType> types = new ArrayList<>();

    String sql = "SELECT t_nom FROM v_type_formation";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      while (resultSet.next()) {
        String name = resultSet.getString(1);

        types.add(new FormationType(name));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return types;
  }

}
