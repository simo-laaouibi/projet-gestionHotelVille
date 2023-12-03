package services;

import java.util.List;

import dao.IDaoRemote;
import dao.VilleIDao;
import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "simo")
@PermitAll
public class VilleService implements VilleIDao,IDaoRemote<Ville> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	
	public  Ville create(Ville o) {
		em.persist(o);
		return o;
	}

	
	@Override
	public boolean delete(Ville o) {
	    if (o != null) {
	        // Check if the entity is managed before trying to remove it
	        if (em.contains(o)) {
	            em.remove(o);
	        } else {
	            // If the entity is detached, merge it first and then remove
	            Ville managedEntity = em.merge(o);
	            em.remove(managedEntity);
	        }
	        return true;
	    }
	    return false;
	}

	@Override
	public Ville update(Ville o) {
	    Ville existingVille = em.find(Ville.class, o.getId());

	    if (existingVille != null) {
	        // Mettre à jour les propriétés de l'entité existante avec les nouvelles valeurs
	        existingVille.setNom(o.getNom());
	      
	        // Ajouter d'autres propriétés à mettre à jour...

	        // Mettre à jour l'entité dans la base de données
	        em.merge(existingVille);
	    }

	    return o;
	}

	@Override
	public Ville findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	@Override
	public List<Ville> findAll() {
		Query query = em.createQuery("select v from Ville v");
		return query.getResultList();
	}

}
