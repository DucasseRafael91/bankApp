package com.bank.daos;
import com.bank.models.Account;
import com.bank.models.AccountType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bank.database.DatabaseConnection;
import com.bank.models.Client;

public class AccountDao {

  public List<Account> getAccountsByClient(String sql, Client client) {
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
            client
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return accounts;
  }

  public boolean updateAccount(Account account) {

    String sql = "UPDATE compte SET solde = ? WHERE id = ?";

    try {
      Connection conn = DatabaseConnection.getInstance().getConnection();

      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setDouble(1, account.getBalance());
      pstmt.setInt(2, account.getId());

      int rowsUpdated = pstmt.executeUpdate();

      return rowsUpdated > 0;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public Account getAccountById(String sql) {
    Account account;
    try {
      // On utilise notre Singleton pour obtenir les infos de connexion
      Connection conn = DatabaseConnection.getInstance().getConnection();

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        account = new Account(rs.getInt("id"), rs.getDouble("solde"), new AccountType(rs.getInt("fk_typeCompte"),rs.getString("type"),rs.getDouble("interets")), new Client(0,"TEST","Test")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }
}

