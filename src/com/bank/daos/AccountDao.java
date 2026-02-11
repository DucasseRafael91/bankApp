package com.bank.daos;
import com.bank.models.Account;
import com.bank.models.AccountType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bank.database.DatabaseConnection;
import com.bank.models.Client;

public class AccountDao {

  public List<Account> getAccountsByClient(String sql) {
    List<Account> accounts = new ArrayList<>();

    try {
      // On utilise notre Singleton pour obtenir les infos de connexion
      Connection conn = DatabaseConnection.getInstance().getConnection();

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        accounts.add(new Account(
            rs.getInt("id"),
            rs.getDouble("solde"),
            new AccountType(rs.getInt("fk_typeCompte"),rs.getString("type"),rs.getDouble("interets")),
            new Client(rs.getInt("fk_client"),rs.getString("nom"),rs.getString("prenom"))
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return accounts;
  }
}

