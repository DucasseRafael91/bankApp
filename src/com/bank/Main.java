package com.bank;

import com.bank.daos.AccountDao;
import com.bank.daos.ClientDao;
import com.bank.daos.OperationDao;
import com.bank.database.SqlQuery;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.models.Operation;
import com.bank.models.OperationType;
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
      System.out.println("3 - Effectuer un dépôt");
      System.out.println("4 - Effectuer un retrait");
      System.out.println("5 - Afficher les opérations d'un compte");
      System.out.println("0 - Quitter");
      System.out.print("Votre choix : ");

      choix = scanner.nextInt();

      switch (choix) {

        case 1:
          getAllClients();
          break;

        case 2:
          // Afficher les comptes d'un client
          List<Client> clients2 = getAllClients();
          System.out.print("Entrez l'ID du client : ");
          int clientId2 = scanner.nextInt();
          Client client2 = null;
          for (Client c : clients2) {
            if (c.getId() == clientId2) {
              client2 = c;
              break;
            }
          }
          getAllAccountsByClient(client2);
          break;

        case 3:
          // Dépôt
          List<Client> clientsDepot = getAllClients();
          System.out.print("Entrez l'ID du client : ");
          int clientIdDepot = scanner.nextInt();
          Client clientDepot = null;
          for (Client c : clientsDepot) {
            if (c.getId() == clientIdDepot) {
              clientDepot = c;
              break;
            }
          }

          List<Account> accountsDepot = getAllAccountsByClient(clientDepot);
          System.out.print("Entrez l'ID du compte : ");
          int accountIdDepot = scanner.nextInt();
          Account selectedAccountDepot = null;
          for (Account a : accountsDepot) {
            if (a.getId() == accountIdDepot) {
              selectedAccountDepot = a;
              break;
            }
          }

          System.out.print("Entrez le montant du dépôt : ");
          double montantDepot = scanner.nextDouble();

          OperationDao operationDaoDepot = new OperationDao();
          operationDaoDepot.createOperation(new Operation(0, montantDepot, selectedAccountDepot, new OperationType(1, "DEPOT")));
          System.out.println("Dépôt effectué !");
          break;

        case 4:
          // Retrait
          List<Client> clientsRetrait = getAllClients();
          System.out.print("Entrez l'ID du client : ");
          int clientIdRetrait = scanner.nextInt();
          Client clientRetrait = null;
          for (Client c : clientsRetrait) {
            if (c.getId() == clientIdRetrait) {
              clientRetrait = c;
              break;
            }
          }

          List<Account> accountsRetrait = getAllAccountsByClient(clientRetrait);
          System.out.print("Entrez l'ID du compte : ");
          int accountIdRetrait = scanner.nextInt();
          Account selectedAccountRetrait = null;
          for (Account a : accountsRetrait) {
            if (a.getId() == accountIdRetrait) {
              selectedAccountRetrait = a;
              break;
            }
          }

          System.out.print("Entrez le montant du retrait : ");
          double montantRetrait = scanner.nextDouble();

          OperationDao operationDaoRetrait = new OperationDao();
          operationDaoRetrait.createOperation(new Operation(0, montantRetrait, selectedAccountRetrait, new OperationType(2, "RETRAIT")));
          System.out.println("Retrait effectué !");
          break;

        case 5:
          // Afficher les opérations d'un compte
          List<Client> clients4 = getAllClients();
          System.out.print("Entrez l'ID du client : ");
          int clientId4 = scanner.nextInt();
          Client client4 = null;
          for (Client c : clients4) {
            if (c.getId() == clientId4) {
              client4 = c;
              break;
            }
          }
          List<Account> accounts4 = getAllAccountsByClient(client4);
          System.out.print("Entrez l'ID du compte : ");
          int accountId4 = scanner.nextInt();
          Account selectedAccount4 = null;
          for (Account a : accounts4) {
            if (a.getId() == accountId4) {
              selectedAccount4 = a;
              break;
            }
          }
          getAllOperationsByAccount(selectedAccount4);
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
