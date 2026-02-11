package com.venteformation.daos;

import java.util.List;

/**
 * Interface Dao.
 */
public interface Dao<T> {

  /**
   * Méthode pour créer un objet dans la base de données.
   *
   * @param obj :objet
   */
  void create(T obj);

  /**
   * Méthode pour mettre à jour un objet dans la base de données.
   *
   * @param obj :objet
   */
  void update(T obj);

  /**
   * Méthode pour supprimer un objet dans la base de données.
   *
   * @param id :id
   */
  void delete(int id);

  /**
   * Méthode pour trouver un objet par id dans la base de données.
   *
   * @param id :id
   * @return : objet à retourner
   */
  T findById(int id);

  /**
   * Méthode pour trouver tous les objets dans la base de données.
   *
   * @return : objets à retourner
   */
  List<T> findAll();
}

