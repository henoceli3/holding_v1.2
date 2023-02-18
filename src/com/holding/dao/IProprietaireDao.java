package com.holding.dao;

import java.util.List;


import com.holding.models.Locataire;
import com.holding.models.Proprietaire;

public interface IProprietaireDao {

 void saveProprietaire(Proprietaire proprietaire);

 void updateProprietaire(Proprietaire proprietaire);

 Proprietaire getProprietaireById(int id_proprietaire);
 
 Proprietaire getProprietaireByUserName (String username);

 List<Proprietaire> getAllLocataire();

 void deleteProprietaire(int id_proprietaire);
 
 List <Proprietaire> rechercher(String nom);

}
