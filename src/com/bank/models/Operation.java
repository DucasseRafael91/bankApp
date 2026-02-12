package com.bank.models;

/**
 * Entité Operation.
 */
public class Operation {
  private int id;
  private double amount;
  private Account account;
  private OperationType operationType;


  // Constructeur
  /**
   * Constructeur de Client.
   */
  public Operation(Integer id, double amount, Account account,OperationType operationType) {
    this.id = id;
    this.amount = amount;
    this.account = account;
    this.operationType = operationType;
  }

  @Override
  public String toString() {
    return "Operation : " +amount + " euros " + operationType.getType();
  }

  // Getters
  public int getId() {
    return id;
  }

  public double getAmount(){
    return amount;
  }

  public Account getAccount() {
    return account;
  }

  public OperationType getOperationType() {
    return operationType;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setAccount(Account account){
    this.account = account;
  }

  public void setOperationType(OperationType operationType){
    this.operationType = operationType;
  }

}
