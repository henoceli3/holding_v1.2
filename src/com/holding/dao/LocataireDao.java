package com.holding.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.holding.models.Locataire;
import com.holding.util.HibernateUtil;

public class LocataireDao implements ILocataireDao {

    // save Locataire
    // get All Locataire
    // get Locataire By Id
    // Update Locataire
    // Delete Locataire

   
	@Override
	public void saveLocataire(Locataire locataire) {
		// TODO Auto-generated method stub
		/*
	     Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start the transaction
	            transaction = session.beginTransaction();

	            // save student object
	            session.save(locataire);

	            // commit the transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	        }*/
		
	        Session session = null;
	    	try {
	  		  session = HibernateUtil.getSessionFactory().openSession();
	  		  session.beginTransaction();
	  		  // votre code ici
	  		  
	  		  // save student object
	            session.save(locataire);
	            
	            
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

	
	/* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.ILocataireDao#updateLocataire()
     */
	@Override
	public void updateLocataire(Locataire locataire) {
		// TODO Auto-generated method stub
	    Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(locataire);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		
	}

	
	/* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.ILocataireDao#getLocataireById()
     */
	@Override
	public Locataire getLocataireById(int id_locataire) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Locataire locataire = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    locataire= session.byId(Locataire.class).getReference(id_locataire);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
		return locataire;
	}

	/* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.ILocataireDao#getAllLocataire()
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<Locataire> getAllLocataire() {
		// TODO Auto-generated method stub
	    Transaction transaction = null;
        List < Locataire > locataires = null;
        /*
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          // votre code ici
          // get students
          locataires = session.createQuery("from Locataire").list();
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
*/
        
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            locataires = session.createQuery("from Locataire").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        
		return locataires;
	}

	/* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.ILocataireDao#deleteLocataire(long)
     */
	@Override
	public void deleteLocataire(int id_locataire) {
		// TODO Auto-generated method stub
		   Transaction transaction = null;
	        Locataire locataire = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start the transaction
	            transaction = session.beginTransaction();

	            locataire = session.get(Locataire.class, id_locataire);
	            // get student object
	            session.delete(locataire);
	            //student = session.load(Student.class, id);
	            // commit the transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	        }
	}

	@Override
	public List<Locataire> rechercher(String nom) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Locataire > locataires = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
           // students = session.createQuery("from Student").list();
            locataires = session.createSQLQuery(nom).addEntity(getClass()).list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		return null;
	}
	
	/*
	 * public List<Student > recupererCommandeByDateAndMenu(String idMenu , Date
	 * dateCommande){ String query =
	 * "SELECT `commande`.`DATECOMMANDE` FROM `commande` WHERE ((`commande`.`ID_MENU` = "
	 * +idMenu+") AND (`commande`.`DATECOMMANDE` ='"+dateCommande+"'))"; //String
	 * query = "SELECT * FROM `` WHERE ((`CODETYPE_LOGEMENT`='"
	 * +typeLogement+"') AND (`CODE_TYPENATIONALITE` ='"
	 * +typeNationalite+"') AND (`CODE_ANNEES`='"+codeAnne+"'))";
	 * 
	 * Commande commande = session.createSQLQuery().addEntity(getClass()).list();
	 * (TypeLogementNationalite)
	 * getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(
	 * TypeLogementNationalite.class).uniqueResult(); return object; }
	 */
    
    
    
}
