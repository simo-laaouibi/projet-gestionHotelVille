package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import dao.VilleIDao;
import entities.Hotel;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */
public class VilleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private VilleIDao ejb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VilleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("delete".equals(action)) {
			String ville = request.getParameter("ville");
			System.out.println(
					"============111111111111111111111111===================================================================");
			int Id = Integer.parseInt(request.getParameter("villeId"));
			Ville villeToDelete = ejb.findById(Id);

			if (villeToDelete != null) {
				boolean deleted = ejb.delete(villeToDelete);

				if (deleted) {
					System.out.println("Ville supprimée avec succès");

				} else {
					System.out.println("La suppression de la ville a échoué");

				}
			} else {
				System.out.println("Ville non trouvée avec l'ID : " + Id);
			}
		} else if ("update".equals(action)) {
			String ville = request.getParameter("ville");
			System.out.println("===============================================================================");
			int id = Integer.parseInt(request.getParameter("id"));

			Ville vUpdate = ejb.findById(id);

			vUpdate.setNom(ville);
			ejb.update(vUpdate);

		}

		// Récupérer la liste mise à jour des villes
		List<Ville> villes = ejb.findAll();
		request.setAttribute("villes", villes);

		// Rediriger vers la page ville.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("create".equals(action)) {
			String nom = request.getParameter("ville");
			ejb.create(new Ville(nom));

			// Après la création, rediriger vers la page VilleController
			response.sendRedirect(request.getContextPath() + "/VilleController");
		} else if ("delete".equals(action)) {
			System.out.println("======================================");
			String idParameter = request.getParameter("id");
			System.out.println(idParameter);
			if (idParameter != null && !idParameter.isEmpty()) {
				int villeId = Integer.parseInt(idParameter);
				System.out.println(villeId);
				Ville villeToDelete = ejb.findById(villeId);
				System.out.println(villeToDelete.getNom());
				if (villeToDelete != null) {

					List<Hotel> hotels = villeToDelete.getHotels();
					handleDeleteAjaxRequest(request, response);
		            
					
					if (hotels.isEmpty()) {
						boolean deleted = ejb.delete(villeToDelete);

						if (deleted) {
							System.out.println("Ville supprimée avec succès");
						} else {
							System.out.println("La suppression de la ville a échoué");
							response.sendRedirect(request.getContextPath() + "/VilleController");
						}
						response.sendRedirect(request.getContextPath() + "/VilleController");
					} else {
						System.out.println("Impossible de faire l'opération, car");
						System.out.println("----La ville que vous tentes de supprimer  contient des Hotels----");
						response.sendRedirect(request.getContextPath() + "/VilleController");
					}
					System.out.println("Impossible de faire l'opération, car");
					System.out.println("----La ville que vous tentes de supprimer  contient des Hotels----");
				} else {
					System.out.println("Ville non trouvée avec l'ID : " + villeId + " La ville contient des hotels");
					response.sendRedirect(request.getContextPath() + "/VilleController");
				}
			} else

				// Après la suppression, rediriger vers la page VilleController
				response.sendRedirect(request.getContextPath() + "/VilleController");

		} else if ("update".equals(action)) {
//			String idParameter = request.getParameter("id");
			String ville = request.getParameter("ville");

			int id = Integer.parseInt(request.getParameter("id"));
			Ville vUpdate = ejb.findById(id);
			System.out.println(ville);

			vUpdate.setNom(ville);
			System.out.println(vUpdate.getNom());
			// Ville v = ejb.create(vUpdate);

			ejb.update(vUpdate);
			System.out.println(vUpdate.getNom());
			response.sendRedirect(request.getContextPath() + "/VilleController?update");
		}
	} private void handleDeleteAjaxRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String villeIdParameter = request.getParameter("villeId");
        if (villeIdParameter != null && !villeIdParameter.isEmpty()) {
            int villeId = Integer.parseInt(villeIdParameter);

            Ville villeToDelete = ejb.findById(villeId);

            if (villeToDelete != null) {
                List<Hotel> hotels = villeToDelete.getHotels();

                if (hotels.isEmpty()) {
                    boolean deleted = ejb.delete(villeToDelete);

                    if (deleted) {
                        // Return a success response, you may customize this based on your needs
                        response.getWriter().write("{'success': true}");
                    } else {
                        // Return an error response, you may customize this based on your needs
                        response.getWriter().write("{'success': false, 'message': 'La suppression de la ville a échoué'}");
                    }
                } else {
                    // Return a response indicating that the ville contains hotels
                    response.getWriter().write("{'success': false, 'message': 'La ville contient des hotels'}");
                }
            } else {
                // Return an error response if the ville is not found
                response.getWriter().write("{'success': false, 'message': 'Ville non trouvée avec l'ID : " + villeId + "'}");
            }
        } else {
            // Return an error response if the ID parameter is missing
            response.getWriter().write("{'success': false, 'message': 'Missing or invalid parameters'}");
        }
    }


}