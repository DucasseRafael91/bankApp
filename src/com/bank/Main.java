package com.bank;

import com.bank.daos.ClientDao;
import com.bank.database.DatabaseConnection;
import com.bank.database.SqlQuery;
import com.bank.models.Client;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

public class Main {

  public static void main(String[] args) {

      // On prépare la requête (Le Builder)
      String sql = new SqlQuery.Builder().table("client").build();

      // On demande au DAO d'aller chercher les données
      ClientDao dao = new ClientDao();
      List<Client> withdraw = dao.getClients(sql);

      // On affiche le résultat
      System.out.println("--- Liste Des Clients ---");
      for (Client client : withdraw) {
        System.out.println(client);
      }
    }
}
