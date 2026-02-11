package com.bank.models;

/**
 * Entité TypeCompte.
 */
public class AccountType {
  private int id;
  private String type;
  private Double interest;


  // Constructeur
  /**
   * Constructeur de TypeCompte.
   */
  public AccountType(Integer id, String type,Double interest) {
    this.id = id;
    this.type = type;
    this.interest = interest;
  }

  @Override
  public String toString() {
    return type + " " + interest;
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Double getInterest() {
    return interest;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setType(String type){
    this.type = type;
  }

  public void setInterest(Double interest){
    this.interest = interest;
  }

}
