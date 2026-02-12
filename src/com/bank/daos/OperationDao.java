package com.bank.daos;
import com.bank.models.Account;
import com.bank.models.Operation;
import com.bank.models.OperationType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bank.database.DatabaseConnection;

public class OperationDao {

  public List<Operation> getOperationsByAccount(String sql, Account account) {
    List<Operation> operations = new ArrayList<>();

    try {
      // On utilise notre Singleton pour obtenir les infos de connexion
      Connection conn = DatabaseConnection.getInstance().getConnection();

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        operations.add(new Operation(
            rs.getInt("id"),
            rs.getDouble("montant"),
            account,
            new OperationType(rs.getInt("fk_typeOperation"),rs.getString("type"))
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return operations;
  }

  public void createOperation(Operation operation) {
    String sql = "INSERT INTO operation (montant, fk_compte, fk_typeOperation) VALUES (?, ?, ?)";

    try {
      Connection conn = DatabaseConnection.getInstance().getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setDouble(1, operation.getAmount());
      stmt.setInt(2, operation.getAccount().getId());
      stmt.setInt(3, operation.getOperationType().getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}

