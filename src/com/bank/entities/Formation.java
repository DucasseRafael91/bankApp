package com.venteformation.entities;

/**
 * Entité Formation.
 */
public class Formation {

  private final String name;
  private final String description;
  private final Integer daysAmount;
  private final Double price;
  private final Category category;
  private int id;

  // Constructeur
  /**
   * Constructeur de Formation.
   */
  public Formation(String name, String description, Integer daysAmount, Double price,
      Category category) {
    this.name = name;
    this.description = description;
    this.daysAmount = daysAmount;
    this.price = price;
    this.category = category;

  }

  @Override
  public String toString() {
    return
        "Nom: " + name + " description: "
            + description
            + " | Durée: " + daysAmount
            + " jours"
            + " | Prix: "
            + price
            + " €"
            + " | Catégorie: " + category;
  }

  // Getters
  public int getId() {
    return id;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Integer getDaysAmount() {
    return daysAmount;
  }

  public Double getPrice() {
    return price;
  }

}
