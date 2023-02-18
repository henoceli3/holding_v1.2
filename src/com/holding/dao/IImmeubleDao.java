package com.holding.dao;

import java.util.List;

import com.holding.models.Immeuble;

public interface IImmeubleDao {
	void saveImmeuble(Immeuble immeuble);

	 void updateImmeuble(Immeuble immeuble);

	 Immeuble getImmeubleById(int id_immeuble);

	 List<Immeuble> getAllImmeuble();

	 void deleteImmeuble(int id_immeuble);
	 
	 List <Immeuble> rechercher(String libelle);
}
