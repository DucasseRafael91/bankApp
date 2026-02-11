package com.venteformation.entities;

/**
 * Entité User.
 */
public class User {

  private final String login;
  private final String password;
  private int id;

  // Constructeur
  /**
   * Constructeur de User.
   */
  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  // Getters
  public int getId() {
    return id;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

}

