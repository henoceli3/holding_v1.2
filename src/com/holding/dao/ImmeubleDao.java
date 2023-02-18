package com.holding.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.holding.models.Immeuble;
import com.holding.util.HibernateUtil;

public class ImmeubleDao implements IImmeubleDao {

	@Override
	public void saveImmeuble(Immeuble immeuble) {
		// TODO Auto-generated method stub
		Session session = null;
    	try {
  		  session = HibernateUtil.getSessionFactory().openSession();
  		  session.beginTransaction();
 
            session.save(immeuble);
            
            
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

	@Override
	public void updateImmeuble(Immeuble immeuble) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(immeuble);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
	}

	@Override
	public Immeuble getImmeubleById(int id_immeuble) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Immeuble immeuble = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();
			    immeuble= session.byId(Immeuble.class).getReference(id_immeuble);
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
		return immeuble;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Immeuble> getAllImmeuble() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Immeuble > immeuble = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get Immeuble
            immeuble = session.createQuery("from Immeuble").list();
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        
		return immeuble;
	}

	@Override
	public void deleteImmeuble(int id_immeuble) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Immeuble immeuble = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            immeuble = session.get(Immeuble.class, id_immeuble);
            // get immeuble object
            session.delete(immeuble);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Immeuble> rechercher(String libelle) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Immeuble > immeuble = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction(); 
            immeuble = session.createSQLQuery(libelle).addEntity(getClass()).list();
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		return null;
	}

}
