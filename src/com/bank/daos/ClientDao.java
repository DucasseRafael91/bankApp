package com.bank.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bank.database.DatabaseConnection;
import com.bank.models.Client;

public class ClientDao {

  public List<Client> getClients(String sql) {
    List<Client> clients = new ArrayList<>();

    try {
      // On utilise notre Singleton pour obtenir les infos de connexion
      Connection conn = DatabaseConnection.getInstance().getConnection();

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        clients.add(new Client(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("prenom")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return clients;
  }
}

