package com.venteformation.entities;

/**
 * Entité Client.
 */
public class Client {

  private final String lastName;
  private final String firstName;
  private final String mail;
  private final String address;
  private final String phoneNumber;
  private final User user;
  private int id;

  // Constructeur
  /**
   * Constructeur de Client.
   */
  public Client(final Integer id, final String lastName, final String firstName, final String mail,
      final String address, final String phoneNumber, final User user) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.mail = mail;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.user = user;
  }

  @Override
  public String toString() {
    return lastName + " " + firstName + " | Email: " + mail + " | Téléphone: " + phoneNumber
        + " | Adresse: " + address;
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

}
