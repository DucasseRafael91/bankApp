package com.venteformation.daos;

import com.venteformation.entities.Category;
import com.venteformation.entities.Formation;
import com.venteformation.entities.FormationType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dao FormationDao.
 */
public class FormationDao implements Dao<Formation> {

  private static final String URL = "jdbc:mariadb://localhost:3307/formation";
  private static final String LOGIN = "root";
  private static final String PASSWORD = "";

  @Override
  public void create(final Formation formation) {
    CategoryDao categorydao = new CategoryDao();
    int id = categorydao.findByName(formation)
    String sqlFormation = "INSERT INTO v_formation (f_nom, f_description, duree_jours, prix, f_fk_categorie) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement ps = connection.prepareStatement(sqlFormation)) {

      ps.setString(1, formation.getName());
      ps.setString(2, formation.getDescription());
      ps.setInt(3, formation.getDaysAmount());
      ps.setDouble(4, formation.getPrice());

      int rows = ps.executeUpdate();
      if (rows > 0) {
        System.out.println("Utilisateur ajouté avec succès !");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(final Formation user) {
    // À implémenter plus tard
  }

  @Override
  public void delete(final int id) {
    // À implémenter plus tard
  }

  /**
   * Méthode pour trouver les formations avec l'id dans la base de données.
   */
  @Override
  public Formation findById(final int id) {
    // À implémenter plus tard
    return null;
  }

  /**
   * Méthode pour trouver toutes les formations dans la base de données.
   */
  @Override
  public ArrayList<Formation> findAll() {
    ArrayList<Formation> formations = new ArrayList<>();

    String sql =
        """
        SELECT f_nom, f_description, duree_jours, prix, c_nom
        FROM v_formation
        INNER JOIN v_categorie
            ON v_formation.f_fk_categorie = v_categorie.c_id
        ORDER BY c_nom;
        """;

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      while (resultSet.next()) {
        String name = resultSet.getString("f_nom");
        String description = resultSet.getString("f_description");
        Integer daysAmount = resultSet.getInt("duree_jours");
        Double price = resultSet.getDouble("prix");

        Category category = new Category(resultSet.getString("c_nom"));
        formations.add(new Formation(name, description, daysAmount, price, category));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return formations;
  }

  /**
   * Méthode pour trouver les formations avec la categorie dans la base de données.
   *
   * @param category : categorie
   * @return retourne les formations par catégorie
   */
  public ArrayList<Formation> findByCategory(final Category category) {
    ArrayList<Formation> formations = new ArrayList<>();

    String sql =
        """
        SELECT f_nom, f_description, duree_jours, prix
        FROM v_formation f
        INNER JOIN v_categorie c
            ON f.f_fk_categorie = c.c_id
        WHERE c.c_nom = ?
        """;

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, category.getName());
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        String name = resultSet.getString("f_nom");
        String description = resultSet.getString("f_description");
        int daysAmount = resultSet.getInt("duree_jours");
        double price = resultSet.getDouble("prix");

        formations.add(new Formation(name, description, daysAmount, price, category));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return formations;
  }

  /**
   * Méthode pour trouver les formations avec le type dans la base de données.
   *
   * @param type : type de la formation
   * @return retourne les formations par type
   */
  public ArrayList<Formation> findByType(final FormationType type) {
    ArrayList<Formation> formations = new ArrayList<>();

    String sql =
        """
        SELECT f_nom, f_description, duree_jours, prix, c_nom
        FROM v_formation
        INNER JOIN est_de_type
            ON f_id = e_fk_formation
        INNER JOIN v_type_formation
            ON t_id = e_fk_type_formation
        INNER JOIN v_categorie
            ON v_formation.f_fk_categorie = v_categorie.c_id
        WHERE t_nom = ?;
        """;

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, type.getName());
      ResultSet resultSet = ps.executeQuery();

      findResult(formations, resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return formations;
  }

  /**
   * Méthode pour trouver le résultat dans la base de données.
   *
   * @param formations : formations à trouver
   * @param resultSet  : formations à trouver
   */
  private void findResult(final ArrayList<Formation> formations, final ResultSet resultSet)
      throws SQLException {
    while (resultSet.next()) {
      String name = resultSet.getString("f_nom");
      String description = resultSet.getString("f_description");
      int daysAmount = resultSet.getInt("duree_jours");
      double price = resultSet.getDouble("prix");

      Category category = new Category(resultSet.getString("c_nom"));
      formations.add(new Formation(name, description, daysAmount, price, category));
    }
  }

  /**
   * Méthode pour trouver les formations avec un mot clé dans la base de données.
   *
   * @param keyword : mot clé
   * @return retourne les formations par mot clé
   */
  public ArrayList<Formation> findByKeyword(final String keyword) {
    ArrayList<Formation> formations = new ArrayList<>();

    String sql =
        """
        SELECT f.f_nom, f.f_description, f.duree_jours, f.prix, c.c_nom
        FROM v_formation f
        INNER JOIN v_categorie c
            ON f.f_fk_categorie = c.c_id
        WHERE f.f_nom LIKE ?
        """;

    try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, "%" + keyword + "%");
      ResultSet resultSet = ps.executeQuery();

      findResult(formations, resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return formations;
  }
}
