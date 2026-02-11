package com.bank;

import com.bank.daos.AccountDao;
import com.bank.daos.ClientDao;
import com.bank.database.SqlQuery;
import com.bank.models.Account;
import com.bank.models.Client;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Client client = new Client(1, "Dumont", "Pierre");
    getAllClients();
    getAllAccountsByClient(client);
  }

  private static void getAllClients() {
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

  private static void getAllAccountsByClient(Client client) {
    // On prépare la requête (Le Builder)

    String sql = new SqlQuery.Builder().table("compte")
        .join("client ON compte.fk_client = client.id")
        .join("typecompte ON compte.fk_typeCompte = typecompte.id")
        .filter("client.id = " + client.getId())
        .build();
    System.out.println(sql);

    // On demande au DAO d'aller chercher les données
    AccountDao dao = new AccountDao();
    List<Account> withdraw = dao.getAccountsByClient(sql);

    // On affiche le résultat
    System.out.println("--- Liste Des comptes ---");
    for (Account account : withdraw) {
      System.out.println(account);
    }
  }
}
