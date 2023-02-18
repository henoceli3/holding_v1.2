package com.holding.dao;

import java.util.List;


import com.holding.models.Locataire;

public interface ILocataireDao {

 void saveLocataire(Locataire locataire);

 void updateLocataire(Locataire locataire);

 Locataire getLocataireById(int id_locataire);

 List<Locataire> getAllLocataire();

 void deleteLocataire(int id_locataire);
 
 List <Locataire> rechercher(String nom);

}
