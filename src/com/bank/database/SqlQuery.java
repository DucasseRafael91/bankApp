package com.bank.database;

public class SqlQuery {

  private String selection = "*"; // Par défaut, on prend tout
  private String table;
  private String condition = "";
  private String sort = "";

  // Le Builder est une classe interne "cuisinier"
  public static class Builder {
    private SqlQuery query = new SqlQuery();

    public Builder table(String table) {
      query.table = table;
      return this; // Permet de chaîner les appels
    }

    public Builder filter(String condition) {
      query.condition = " WHERE " + condition;
      return this;
    }

    public Builder sort(String column) {
      query.sort = " ORDER BY " + column;
      return this;
    }

    // La touche finale qui livre la requête
    public String build() {
      return "SELECT " + query.selection + " FROM " + query.table + query.condition + query.sort + ";";
    }
  }

}
