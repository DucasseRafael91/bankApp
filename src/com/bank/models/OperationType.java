package com.bank.models;

/**
 * Entité TypeOperation.
 */
public class OperationType {
  private int id;
  private String type;

  // Constructeur
  /**
   * Constructeur de TypeOperation.
   */
  public OperationType(Integer id, String type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setType(String type){
    this.type = type;
  }

}
