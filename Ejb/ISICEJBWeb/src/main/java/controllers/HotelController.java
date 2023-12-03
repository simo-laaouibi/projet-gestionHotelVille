package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.HotelIDao;
import dao.VilleIDao;
import entities.Hotel;
import entities.Ville;

/**
 * Servlet implementation class HotelController
 */

public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private HotelIDao ejb;
	@EJB
	private VilleIDao ejbi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String adresse = request.getParameter("adresse");
		String telephone = request.getParameter("telephone");
		String action = request.getParameter("action");
		if ("delete".equals(action)) {
			int Id = Integer.parseInt(request.getParameter("id"));
			Hotel hotelToDelete = ejb.findById(Id);

			if (hotelToDelete != null) {
				boolean deleted = ejb.delete(hotelToDelete);

				if (deleted) {
					System.out.println("Ville supprimée avec succès");
					
				} else {
					System.out.println("La suppression de la ville a échoué");
					
				}
			} else {
				System.out.println("Ville non trouvée avec l'ID : " + Id);
			}	
		}  else if ("update".equals(action)) {
			
			System.out.println("===============================================================================1");
			int id = Integer.parseInt(request.getParameter("id"));
			int idVille = Integer.parseInt(request.getParameter("idVille"));
			Hotel hUpdate = ejb.findById(id);

			hUpdate.setNom(nom);
			hUpdate.setAdresse(adresse);
			hUpdate.setTelephone(telephone);
			hUpdate.setVille(ejbi.findById(idVille));
			ejb.update(hUpdate);
		} else if ("create".equals(action)) {

			String villeParameter = request.getParameter("ville");

			if (villeParameter != null && !villeParameter.isEmpty()) {
				int villeId = Integer.parseInt(villeParameter);
				Ville selectedVille = ejbi.findById(villeId);
				ejb.create(new Hotel(nom, adresse, telephone, selectedVille));
			} else {
				System.out.println("erroooooooor");
			}
		}

		// Récupérer la liste mise à jour des villes
		List<Ville> villes = ejbi.findAll();
		request.setAttribute("villes", villes);
		List<Hotel> hotels = ejb.findAll();
		request.setAttribute("hotels", hotels);
		// Rediriger vers la page ville.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("hotel.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if ("create".equals(action)) {
			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String telephone = request.getParameter("telephone");
			String idVilleParameter = request.getParameter("ville");
			if (idVilleParameter != null && !idVilleParameter.isEmpty()) {
				int idVille = Integer.parseInt(idVilleParameter);
				ejb.create(new Hotel(nom, adresse, telephone, ejbi.findById(idVille)));
			}

			// Après la création, rediriger vers la page VilleController
			response.sendRedirect(request.getContextPath() + "/HotelController");
		} if ("delete".equals(action)) {
			int Id = Integer.parseInt(request.getParameter("id"));
			Hotel hotelToDelete = ejb.findById(Id);

			if (hotelToDelete != null) {
				boolean deleted = ejb.delete(hotelToDelete);

				if (deleted) {
					System.out.println("Ville supprimée avec succès");
					
				} else {
					System.out.println("La suppression de la ville a échoué");
					
				}
			} else {
				System.out.println("Ville non trouvée avec l'ID : " + Id);
			}	
			response.sendRedirect(request.getContextPath() + "/HotelController");
		} else if ("update".equals(action)) {
			System.out.println("==========================================================2");
		    String idVilleParam = request.getParameter("ville");
		    String idParam = request.getParameter("id");
		    String nom = request.getParameter("nom");
		    String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            
            
		    System.out.println(idVilleParam + idParam +" " +nom);
		    if (idVilleParam != null && idParam != null) {
		        try {
		            int idVille = Integer.parseInt(idVilleParam);
		            int id = Integer.parseInt(idParam);

		            
		            

		            Hotel vUpdate = ejb.findById(id);
		            System.out.println(vUpdate.getNom()+ " "+ vUpdate.getAdresse()+ " "+vUpdate.getTelephone());
		            if (vUpdate != null) {
		            	 System.out.println(vUpdate.getNom()+ " "+ vUpdate.getAdresse()+ " "+vUpdate.getTelephone());
		                vUpdate.setNom(nom);
		                vUpdate.setAdresse(adresse);
		                vUpdate.setTelephone(telephone);
		                vUpdate.setVille(ejbi.findById(idVille));
		                System.out.println(nom+ "TAL3O "+ adresse+ " "+telephone +ejbi.findById(idVille) );
		                ejb.update(vUpdate);
		                System.out.println("==========================================8");
		                response.sendRedirect(request.getContextPath() + "/HotelController");
		            } else {
		                System.out.println("Hotel non trouvé avec l'ID : " + id);
		            }
		        } catch (NumberFormatException e) {
		            System.out.println("Erreur de format pour l'ID de l'hotel ou de la ville");
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("L'ID de l'hotel ou de la ville est manquant");
		    }
		}

	}
		
	

}
