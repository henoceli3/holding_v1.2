package com.holding.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.holding.models.Locataire;
import com.holding.models.Proprietaire;
import com.holding.util.HibernateUtil;

public class ProprietaireDao implements IProprietaireDao {
	//	Save proprietaire
	// 	Update
	//  GetbyId
	//	GetByUserName
	//	Recherche
	//	Delete

	
	/**
	 * Enregistrer un proprietaire
	 */
	@Override
	public void saveProprietaire(Proprietaire proprietaire) {
	     Session session = null;
	    	try {
	  		  session = HibernateUtil.getSessionFactory().openSession();
	  		  session.beginTransaction();
	  		  // votre code ici
	  		  
	  		  // save student object
	            session.save(proprietaire);
	            
	            
	  		  session.getTransaction().commit();
	  		} catch (Exception e) {
	  		  if (session != null) {
	  		    session.getTransaction().rollback();
	  		  }
	  		  throw e;
	  		} finally {
	  		  if (session != null) {
	  		    session.close();
	  		  }
	  		}
	}

	
	/**
	 * Mise a jour d'un proprietaire
	 */
	@Override
	public void updateProprietaire(Proprietaire proprietaire) {
		   Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start the transaction
	            transaction = session.beginTransaction();

	            // save student object
	            session.saveOrUpdate(proprietaire);

	            // commit the transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	        }
	}

	@Override
	public Proprietaire getProprietaireById(int id_proprietaire) {	
	Transaction transaction = null;
    Proprietaire proprietaire = null;
    try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		    // start the transaction
		    transaction = session.beginTransaction();

		    // get proprietaire object
		    proprietaire= session.byId(Proprietaire.class).getReference(id_proprietaire);
		     // or student = session.get(Student.class, id);
		    //or student = session.load(Student.class, id);
		   //or commit the transaction
		    transaction.commit();
	} catch (Exception e) {
		if (transaction != null) {
            transaction.rollback();
	}
		
	}
	return proprietaire;
	}

	@Override
	public Proprietaire getProprietaireByUserName(String nom_utilisateur) {
		Transaction transaction = null;
	    Proprietaire proprietaire = null;
	    try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get proprietaire object
			    //proprietaire= session.byId(Proprietaire.class).getReference(username);
			    //proprietaire = session.createSQLQuery(nom_utilisateur).getResultList());
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
	            transaction.rollback();
		}
			
		}
		return proprietaire;
	}

	@Override
	public List<Proprietaire> getAllLocataire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProprietaire(int id_proprietaire) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proprietaire> rechercher(String nom) {
	return null;
	}


    
    
    
}
