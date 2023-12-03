import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.HotelIDao;
import dao.IDaoRemote;
import dao.VilleIDao;
import entities.Hotel;
import entities.Ville;
import jakarta.ejb.EJB;

public class TestHotel {
	public static IDaoRemote<Hotel> lookUpEmployeRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDaoRemote<Hotel>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/Laili!dao.IDaoRemote");

	}
	public static IDaoRemote<Ville> lookUpVilleRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDaoRemote<Ville>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/Asmaa!dao.IDaoRemote");

	}
	public static void main(String[] args) {
		
		try {
			IDaoRemote<Hotel> dao = lookUpEmployeRemote();
			IDaoRemote<Ville> dao2 = lookUpVilleRemote();
			dao.create(new Hotel("he", "as", "44", dao2.findById(3) ));
		

			for(Hotel v : dao.findAll()) {
				Ville ville = dao2.findById(v.getId());
				System.out.println(v.getNom() +" " +v.getAdresse()+" " + v.getTelephone()+" " + v.getVille());
			}
			
			
			for(Hotel v : dao.findAll()) {
				Ville ville = dao2.findById(v.getId());
				System.out.println(v.getNom() +" " +v.getAdresse()+" " + v.getTelephone()+" " + v.getVille());
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("An error occurred: " + e.getMessage());
	        e.printStackTrace();
		}
		

	}
}
