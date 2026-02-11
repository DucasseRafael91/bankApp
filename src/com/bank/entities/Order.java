package com.venteformation.entities;

import java.time.LocalDateTime;

/**
 * Entité Order.
 */
public class Order {

  private final LocalDateTime date;
  private final Client client;
  private int id;

  // Constructeur
  /**
   * Constructeur de Order.
   */
  public Order(LocalDateTime date, Client client) {
    this.client = client;
    this.date = date;
  }

  // Getters
  public int getId() {
    return id;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public Client getClient() {
    return client;
  }

}
