package com.venteformation.daos;

import com.venteformation.entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dao User.
 */
public class UserDao implements Dao<User> {

  private static final String URL = "jdbc:mariadb://localhost:3307/formation";
  private static final String LOGIN = "root";
  private static final String PASSWORD = "";


  /**
   * Méthode pour créer un utilisateur dans la base de données.
   *
   * @param user : utilisateur
   */
  @Override
  public void create(final User user) {
    String sql = "INSERT INTO v_utilisateur (u_identifiant, u_mot_de_passe) VALUES (?, ?)";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, user.getLogin());
      ps.setString(2, user.getPassword());

      int rows = ps.executeUpdate();
      if (rows > 0) {
        System.out.println("Utilisateur ajouté avec succès !");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(final User user) {
    // À implémenter plus tard
  }

  @Override
  public void delete(final int id) {
    // À implémenter plus tard
  }


  /**
   * Méthode pour trouver l'user avec l'id dans la base de données.
   *
   * @param id :id
   */
  @Override
  public User findById(final int id) {
    // À implémenter plus tard
    return null;
  }


  /**
   * Méthode pour trouver tous les utilisateurs dans la base de données.
   */
  @Override
  public ArrayList<User> findAll() {
    ArrayList<User> users = new ArrayList<>();

    String sql = "SELECT u_identifiant, u_mot_de_passe FROM v_utilisateur";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      while (resultSet.next()) {
        String login = resultSet.getString("u_identifiant");
        String password = resultSet.getString("u_mot_de_passe");

        users.add(new User(login, password));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  /**
   * Méthode pour se connecter.
   *
   * @param user : utilisateur
   * @return : user si correspond
   */
  public User connexion(final User user) {

    String sql =
        """
        SELECT u_identifiant, u_mot_de_passe
        FROM v_utilisateur
        WHERE u_identifiant = ? AND u_mot_de_passe = ?
        """;

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return new User(
            resultSet.getString("u_identifiant"),
            resultSet.getString("u_mot_de_passe")
        );
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

}
