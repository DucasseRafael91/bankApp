package com.bank.models;

/**
 * Entité Client.
 */
public class Client {
  private int id;
  private String lastName;
  private String firstName;


  // Constructeur
  /**
   * Constructeur de Client.
   */
  public Client(Integer id, String lastName,String firstName) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  @Override
  public String toString() {
    return lastName + " " + firstName;
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }

}
