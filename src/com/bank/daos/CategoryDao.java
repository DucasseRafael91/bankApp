package com.venteformation.daos;

import com.venteformation.entities.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dao CategoryDao.
 */
public class CategoryDao implements Dao<Category> {

  /**
   * DAO permettant la gestion des catégories dans la base de données.
   */

  private static final String URL = "jdbc:mariadb://localhost:3307/formation";
  private static final String LOGIN = "formation";
  private static final String PASSWORD = "formation";

  @Override
  public void create(final Category category) {
    // À implémenter plus tard
  }

  @Override
  public void update(final Category category) {
    // À implémenter plus tard
  }

  @Override
  public void delete(final int id) {
    // À implémenter plus tard
  }

  /**
   * Méthode pour trouver une categorie par id dans la base de données.
   */
  @Override
  public Category findById(final int id) {
    // À implémenter plus tard
    return null;
  }

  public int findByName(final Category category) {
    String sql = "SELECT c_id FROM v_categorie WHERE c_nom = ?";
    int id = -1; // valeur par défaut si non trouvé

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

      preparedStatement.setString(1, category.getName());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          id = resultSet.getInt("c_id");
        }
      }

    } catch (SQLException e) {
      System.out.println("Erreur dans la requête SQL (findByName)");
    }

    return id;
  }

  /**
   * Méthode pour trouver toute les categories dans la base de données.
   */
  @Override
  public ArrayList<Category> findAll() {
    ArrayList<Category> categories = new ArrayList<>();

    String sql = "SELECT c_nom FROM v_categorie";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      while (resultSet.next()) {
        String name = resultSet.getString(1);
        categories.add(new Category(name));
      }

    } catch (SQLException e) {
      System.out.println("Erreur dans la requête SQL");
    }

    return categories;
  }
}
