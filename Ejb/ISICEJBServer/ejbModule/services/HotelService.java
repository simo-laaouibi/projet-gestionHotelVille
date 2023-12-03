package services;

import java.util.List;

import dao.IDaoRemote;
import dao.HotelIDao;
import entities.Hotel;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "laaouibi")
@PermitAll
public class HotelService implements HotelIDao,IDaoRemote<Hotel> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	
	public  Hotel create(Hotel o) {
		em.persist(o);
		return o;
	}

	
	@Override
	public boolean delete(Hotel o) {
	    if (o != null) {
	        // Check if the entity is managed before trying to remove it
	        if (em.contains(o)) {
	            em.remove(o);
	        } else {
	            // If the entity is detached, merge it first and then remove
	            Hotel managedEntity = em.merge(o);
	            em.remove(managedEntity);
	        }
	        return true;
	    }
	    return false;
	}

	@Override
	public Hotel update(Hotel o) {
	    Hotel existingHotel = em.find(Hotel.class, o.getId());

	    if (existingHotel != null) {
	        // Mettre à jour les propriétés de l'entité existante avec les nouvelles valeurs
	        existingHotel.setNom(o.getNom());
	        existingHotel.setAdresse(o.getAdresse());
	        existingHotel.setTelephone(o.getTelephone());
	        existingHotel.setVille(o.getVille());
	        // Ajouter d'autres propriétés à mettre à jour...

	        // Mettre à jour l'entité dans la base de données
	        em.merge(existingHotel);
	    }

	    return o;
	}

	@Override
	public Hotel findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Hotel.class, id);
	}

	@Override
	public List<Hotel> findAll() {
		Query query = em.createQuery("select h from Hotel h");
		return query.getResultList();
	}

}
