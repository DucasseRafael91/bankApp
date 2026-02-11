package com.bank;

import com.bank.daos.AccountDao;
import com.bank.daos.ClientDao;
import com.bank.daos.OperationDao;
import com.bank.database.SqlQuery;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.models.Operation;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Client client = new Client(1, "Dumont", "Pierre");
    getAllClients();
    List<Account> accounts  = getAllAccountsByClient(client);
    getAllOperationsByAccount(accounts.get(0));
  }

  private static void getAllClients() {
    // On prépare la requête (Le Builder)
    String sql = new SqlQuery.Builder().table("client").build();

    // On demande au DAO d'aller chercher les données
    ClientDao dao = new ClientDao();
    List<Client> clients = dao.getClients(sql);

    // On affiche le résultat
    System.out.println("--- Liste Des Clients ---");
    for (Client client : clients) {
      System.out.println(client);
    }
  }

  private static List<Account> getAllAccountsByClient(Client client) {
    // On prépare la requête (Le Builder)

    String sql = new SqlQuery.Builder().table("compte")
        .join("client ON compte.fk_client = client.id")
        .join("typecompte ON compte.fk_typeCompte = typecompte.id")
        .filter("client.id = " + client.getId())
        .build();

    // On demande au DAO d'aller chercher les données
    AccountDao dao = new AccountDao();
    List<Account> accounts = dao.getAccountsByClient(sql, client);

    // On affiche le résultat
    System.out.println("--- Liste des comptes ---");
    for (Account account : accounts) {
      System.out.println(account);
    }
    return accounts;
  }

  private static void getAllOperationsByAccount(Account account) {
    // On prépare la requête (Le Builder)

    String sql = new SqlQuery.Builder().table("operation")
        .join("typeoperation ON `fk_typeOperation` = typeoperation.id")
        .filter("fk_compte = " + account.getId())
        .build();

    // On demande au DAO d'aller chercher les données
    OperationDao dao = new OperationDao();
    List<Operation> operations = dao.getOperationsByAccount(sql, account);

    // On affiche le résultat
    System.out.println("--- Liste des comptes ---");
    for (Operation operation : operations) {
      System.out.println(operation);
    }
  }
}
