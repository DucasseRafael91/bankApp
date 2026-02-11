package com.bank;

import com.bank.daos.AccountDao;
import com.bank.daos.ClientDao;
import com.bank.daos.OperationDao;
import com.bank.database.SqlQuery;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.models.Operation;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int choix;

    do {
      System.out.println("\n===== MENU BANQUE =====");
      System.out.println("1 - Afficher tous les clients");
      System.out.println("2 - Afficher les comptes d'un client");
      System.out.println("3 - Afficher les opérations d'un compte");
      System.out.println("0 - Quitter");
      System.out.print("Votre choix : ");

      choix = scanner.nextInt();

      switch (choix) {

        case 1:
          getAllClients();
          break;

        case 2:
          getAllClients();
          System.out.print("Entrez l'ID du client : ");
          int clientId = scanner.nextInt();
          Client client = getAllClients().get(clientId - 1);
          getAllAccountsByClient(client);
          break;

        case 3:
          List<Client> clients = getAllClients();

          System.out.print("Entrez l'ID du client : ");
          int clientIdChoice = scanner.nextInt();

          Client selectedClient = null;
          for (Client c : clients) {
            if (c.getId() == clientIdChoice) {
              selectedClient = c;
              break;
            }
          }

          List<Account> accounts = getAllAccountsByClient(selectedClient);

          System.out.print("Entrez l'ID du compte : ");
          int accountIdChoice = scanner.nextInt();

          Account selectedAccount = null;
          for (Account a : accounts) {
            if (a.getId() == accountIdChoice) {
              selectedAccount = a;
              break;
            }
          }

          getAllOperationsByAccount(selectedAccount);
          break;

        case 0:
          System.out.println("Au revoir !");
          break;

        default:
          System.out.println("Choix invalide.");
      }

    } while (choix != 0);

    scanner.close();
  }


  private static List<Client> getAllClients() {
    // On prépare la requête (Le Builder)
    String sql = new SqlQuery.Builder().table("client").build();

    // On demande au DAO d'aller chercher les données
    ClientDao dao = new ClientDao();
    List<Client> clients = dao.getClients(sql);

    // On affiche le résultat
    System.out.println("--- Liste des Clients ---");
    for (Client client : clients) {
      System.out.println(client);
    }
    return clients;
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
    System.out.println("--- Liste des operations ---");
    for (Operation operation : operations) {
      System.out.println(operation);
    }
  }
}
