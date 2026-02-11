package com.bank.models;

/**
 * Entité Account.
 */
public class Account {
  private int id;
  private double balance;
  private AccountType accountType;
  private Client client;


  // Constructeur
  /**
   * Constructeur de Account.
   */
  public Account(Integer id, Double balance,AccountType accountType, Client client) {
    this.id = id;
    this.balance = balance;
    this.accountType = accountType;
    this.client = client;
  }

  @Override
  public String toString() {
    return balance + " " + accountType.getType() +  " " + client.getLastName() + " " + client.getFirstName();
  }

  // Getters
  public int getId() {
    return id;
  }

  public Double getBalance() {
    return balance;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public Client getClient() {
    return client;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setBalance(Double balance){
    this.balance = balance;
  }

  public void setAccountType(AccountType accountType){
    this.accountType = accountType;
  }

  public void setClient(Client client){
    this.client = client;
  }
}
