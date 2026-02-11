package com.venteformation.daos;

import com.venteformation.entities.Formation;
import com.venteformation.entities.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dao Order.
 */
public class OrderDao implements Dao<Order> {

  private static final String URL = "jdbc:mariadb://localhost:3307/formation";
  private static final String LOGIN = "root";
  private static final String PASSWORD = "";

  /**
   * Méthode pour créer des commandes dans la base de données.
   *
   * @param order      : commande à insérer dans la BDD
   * @param formations : liste des formations commandées
   */
  public void create(final Order order, final ArrayList<Formation> formations) {
    String sqlCommande = "INSERT INTO v_commande (c_date, c_fk_client) "
                    + "VALUES (CURRENT_TIMESTAMP, ?)";
    String sqline = "INSERT INTO v_ligne_commande (l_fk_formation, l_fk_commande) "
                    + "VALUES (?, ?)";
    String sqlSelectFormation = "SELECT f_id FROM v_formation WHERE f_nom = ?";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {

      connection.setAutoCommit(false);

      try (PreparedStatement psCommande =
          connection.prepareStatement(sqlCommande, Statement.RETURN_GENERATED_KEYS)) {

        psCommande.setInt(1, order.getClient().getId());
        psCommande.executeUpdate();

        try (ResultSet rs = psCommande.getGeneratedKeys()) {
          if (rs.next()) {
            order.setId(rs.getInt(1));
          }
        }
      }

      try (PreparedStatement psSelectFormation = connection.prepareStatement(sqlSelectFormation);
          PreparedStatement psLigne = connection.prepareStatement(sqline)) {

        for (Formation f : formations) {

          psSelectFormation.setString(1, f.getName());
          try (ResultSet rs = psSelectFormation.executeQuery()) {
            if (rs.next()) {
              int formationId = rs.getInt("f_id");

              psLigne.setInt(1, formationId);
              psLigne.setInt(2, order.getId());
              psLigne.executeUpdate();
            } else {
              System.out.println("La formation '"
                  + f.getName()
                  + "' n'existe pas en base !");
            }
          }
        }
      }

      connection.commit();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void create(final Order obj) {
    // à implémenter plus tard
  }

  @Override
  public void update(final Order order) {
    // À implémenter plus tard
  }

  @Override
  public void delete(final int id) {
    // À implémenter plus tard
  }

  /**
   * Méthode pour trouver les commandes avec l'id dans la base de données.
   *
   * @param id : id
   */
  @Override
  public Order findById(final int id) {
    // À implémenter plus tard
    return null;
  }

  /**
   * Méthode pour trouver toutes les commandes dans la base de données.
   */
  @Override
  public ArrayList<Order> findAll() {
    return new ArrayList<>();
  }

}
