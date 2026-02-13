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
    int choice = -1;
    String operationType = "";
    int clientId;
    Client client;
    int accountId = 0;
    Account account;

    while (choice != 0) {

      System.out.println("\n===== MENU BANQUE =====");
      System.out.println("1 - Afficher tous les clients");
      System.out.println("2 - Afficher les comptes d'un client");
      System.out.println("3 - Effectuer un dépôt");
      System.out.println("4 - Effectuer un retrait");
      System.out.println("5 - Afficher les opérations d'un compte");
      System.out.println("0 - Quitter");
      System.out.print("Votre choix : ");

      choice = scanner.nextInt();

      switch (choice) {

        case 1:
          getAllClients();
          break;

        case 2:
          getAllClients();
          clientId = chooseClientId(scanner);
          client = getClientById(clientId);
          getAllAccountsByClient(client);
          break;

        case 3:
          operationType = "DEPOT";
          makeAnOperation(scanner,operationType);
          break;

        case 4:
          operationType = "RETRAIT";
          makeAnOperation(scanner,operationType);
          break;

        case 5:
          getAllClients();
          clientId = chooseClientId(scanner);
          client = getClientById(clientId);

          if (client == null) {
            System.out.println("Client introuvable !");
            break;
          }

          getAllAccountsByClient(client);
          accountId = chooseAccountId(scanner);
          account = getAccountById(accountId);

          if (account == null) {
            System.out.println("Compte introuvable !");
            break;
          }

          getAllOperationsByAccount(account);
          break;

        case 0:
          System.out.println("Au revoir !");
          break;

        default:
          System.out.println("Choix invalide.");
      }
    }
    scanner.close();
  }

  private static void makeAnOperation(Scanner scanner, String operationType) {
    int clientId;
    Account account;
    int accountId;
    Client client;
    getAllClients();
    clientId = chooseClientId(scanner);
    client = getClientById(clientId);
    getAllAccountsByClient(client);
    accountId = chooseAccountId(scanner);
    account = getAccountById(accountId);
    createOperation(scanner,account, operationType);
  }

  private static void createOperation(Scanner scanner, Account selectedAccount, String operationType) {
    System.out.print("Entrez le montant du " + operationType + " : ");
    double montant = scanner.nextDouble();
    if (operationType.equals("RETRAIT") && montant > selectedAccount.getBalance()){
      System.out.println("Solde Insuffisant");
    }
    else if (operationType.equals("DEPOT")){
      OperationDao operationDao = new OperationDao();
      operationDao.createOperation(new Operation(0, montant, selectedAccount, new OperationType(1, operationType)));
      AccountDao accountDao = new AccountDao();
      selectedAccount.setBalance(selectedAccount.getBalance() + montant);
      accountDao.updateAccount(selectedAccount);
    }
    else{
      OperationDao operationDao = new OperationDao();
      operationDao.createOperation(new Operation(0, montant, selectedAccount, new OperationType(2, operationType)));
      AccountDao accountDao = new AccountDao();
      selectedAccount.setBalance(selectedAccount.getBalance() - montant);
      accountDao.updateAccount(selectedAccount);
    }

  }

  private static int chooseAccountId(Scanner scanner) {
    System.out.print("Entrez l'ID du compte : ");
    int accountId = scanner.nextInt();
    return accountId;
  }

  private static int chooseClientId(Scanner scanner) {
    System.out.print("Entrez l'ID du client : ");
    int clientId = scanner.nextInt();
    return clientId;
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

  private static Account getAccountById(int id) {

    String sql = new SqlQuery.Builder().table("compte")
        .join("client ON compte.fk_client = client.id")
        .join("typecompte ON compte.fk_typeCompte = typecompte.id")
        .filter("compte.id = " + id)
        .build();

    AccountDao dao = new AccountDao();
    Account account = dao.getAccountById(sql);

    if (account == null) {
      return null;
    }

    System.out.println("--- Compte Sélectionné ---");
    System.out.println(account);

    return account;
  }

  private static Client getClientById(int id) {

    String sql = new SqlQuery.Builder().table("client")
        .filter("client.id = " + id)
        .build();

    ClientDao dao = new ClientDao();
    Client client = dao.getClientById(sql);
    System.out.println("--- Compte Sélectionné ---");
    System.out.println(client);
    return client;
  }

  private static void getAllOperationsByAccount(Account account) {
    // On prépare la requête (Le Builder)

    String sql = new SqlQuery.Builder().table("operation")
        .join("typeoperation ON `fk_typeOperation` = typeoperation.id")
        .filter("fk_compte = " + account.getId())
        .build();

    OperationDao dao = new OperationDao();
    List<Operation> operations = dao.getOperationsByAccount(sql, account);

    // On affiche le résultat
    System.out.println("--- Liste des operations ---");
    for (Operation operation : operations) {
      System.out.println(operation);
    }
  }
}
