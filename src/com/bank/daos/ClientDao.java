package com.venteformation.daos;

import com.venteformation.entities.Client;
import com.venteformation.entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Dao ClientDao.
 */
public class ClientDao implements Dao<Client> {

  private static final String URL = "jdbc:mariadb://localhost:3307/formation";
  private static final String LOGIN = "root";
  private static final String PASSWORD = "";

  @Override
  public void create(final Client client) {
    // À implémenter plus tard
  }

  @Override
  public void update(final Client client) {
    // À implémenter plus tard
  }

  @Override
  public void delete(final int id) {
    // À implémenter plus tard
  }

  /**
   * Méthode pour trouver les clients avec l'id dans la base de données.
   */
  @Override
  public Client findById(final int id) {
    // À implémenter plus tard
    return null;
  }

  /**
   * Méthode pour trouver tous les clients dans la base de données.
   */
  @Override
  public ArrayList<Client> findAll() {
    return new ArrayList<>();
  }

  /**
   * Méthode pour trouver les clients d'un utilisateur dans la base de données.
   *
   * @param user : utilisateur
   * @return clients : clients de l'utilisateur
   */
  public ArrayList<Client> findByUser(final User user) {
    ArrayList<Client> clients = new ArrayList<>();

    String sql =
        "SELECT c_nom, c_prenom, c_email, c_adresse, c_telephone, c_id "
            + "FROM v_client "
            + "INNER JOIN v_utilisateur ON c_fk_utilisateur = u_id "
            + "WHERE u_identifiant = ?";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

      preparedStatement.setString(1, user.getLogin());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          String lastName = resultSet.getString("c_nom");
          String firstName = resultSet.getString("c_prenom");
          String mail = resultSet.getString("c_email");
          String address = resultSet.getString("c_adresse");
          String phone = resultSet.getString("c_telephone");
          Integer id = resultSet.getInt("c_id");

          clients.add(new Client(
              id,
              lastName,
              firstName,
              mail,
              address,
              phone,
              user
          ));
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return clients;
  }

}
