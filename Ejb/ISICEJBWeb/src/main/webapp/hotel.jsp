<%@page import="entities.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Hotel Management</title>
<!-- Add Bootstrap CDN links -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .container {
        margin-top: 50px;
    }

    form {
        margin-bottom: 20px;
    }

    label {
        font-weight: bold;
    }

    table {
        margin-top: 20px;
        width: 100%;
    }

    th, td {
        text-align: center;
        border: 1px solid #dee2e6;
        padding: 8px;
    }

    th {
        background-color: #343a40;
        color: #fff;
    }

    .btn-success, .btn-danger, .btn-warning {
        width: 80px;
    }

    .sidebar {
        display: flex;
        justify-content: space-between; /* Distribute space between items */
        align-items: center; /* Center items vertically */
        width: 100%; /* Fill the entire width */
        background-color: #007bff; /* Blue background color */
        padding: 10px; /* Optional: Add padding for better aesthetics */
    }

    .sidebar form {
        /* Optional: Adjust margin or padding if needed */
    }
</style>

</head>
<body class="bg-light">

 <!-- Sidebar -->
    <div class="sidebar">
        <!-- Home Button -->
        <form action="Home.jsp" class="mb-3">
            <button class="btn btn-primary btn-block">Home</button>
        </form>

        <!-- Gestion des Villes Button -->
        <form action="VilleController" class="mb-3">
            <button class="btn btn-primary btn-block">Gestion des Villes</button>
        </form>

        <!-- Gestion des Hotels Button -->
        <form action="HotelController" class="mb-3">
            <button class="btn btn-primary btn-block">Gestion des Hotels</button>
        </form>
    </div>

	<div class="container">
		<form action="HotelController" method="post" class="form-inline">
			<div class="form-group mb-2">
				<label for="nom" class="mr-2">Nom :</label> <input type="text"
					name="nom" class="form-control mr-2" />
			</div>
			<div class="form-group mb-2">
				<label for="adresse" class="mr-2">Adresse :</label> <input
					type="text" name="adresse" class="form-control mr-2" />
			</div>
			<div class="form-group mb-2">
				<label for="telephone" class="mr-2">Téléphone :</label> <input
					type="text" name="telephone" class="form-control mr-2" />
			</div>
			<div class="form-group mb-2">
				<label for="ville" class="mr-2">Villes:</label> <select name="ville"
					class="form-control mr-2" id="modalhotelVille">
					<c:forEach items="${villes}" var="v">
						<option value="${v.id}">${v.nom}</option>
					</c:forEach>
				</select>
			</div>
			<input type="hidden" name="action" value="create" />
			<button type="submit" class="btn btn-primary mb-2">Enregistrer</button>
		</form>

		<!-- Add an input field for Ville filtering above the table -->
		<label for="villeFilter">Filtrer par Ville:</label> <input type="text"
			id="villeFilter" oninput="filterTable()" class="form-control mb-3" />

		<!-- Your existing table structure -->
		<!-- Your existing table structure -->
<h1>Liste des Hotels:</h1>
<table id="hotelsTable" class="table table-bordered table-striped">
    <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Adresse</th>
            <th>Telephone</th>
            <th>Ville</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${hotels}" var="v">
            <tr>
                <td>${v.id}</td>
                <td>${v.nom}</td>
                <td>${v.adresse}</td>
                <td>${v.telephone}</td>
                <td>${v.ville.nom}</td>
                <td class="actions">
                    <form action="HotelController" method="post" onsubmit="return confirmDelete()">
                        <input type="hidden" name="action" value="delete" />
                        <input type="hidden" name="Id" value="${v.id}" />
                        <button type="submit" class="btn btn-danger">Supprimer</button>
                    </form>
                    <form action="HotelController" method="post" onsubmit="return confirmModification()">
                        <input type="hidden" name="villeId" value="${v.id}" />
                        <button type="submit" class="btn btn-warning">Modifier</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Add Bootstrap JS and Popper.js CDN links if needed -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script>
    function confirmDelete() {
        return confirm("Êtes-vous sûr de vouloir supprimer ?");
    }
</script>
		
		<script>
    function confirmModification() {
        return confirm("Êtes-vous sûr de vouloir Modifier ?");
    }
</script>

	<!-- Add Bootstrap JS and Popper.js CDN links if needed -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script>
		function filterTable() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("villeFilter");
			filter = input.value.toUpperCase();
			table = document.getElementById("hotelsTable");
			tr = table.getElementsByTagName("tr");

			// Loop through all table rows and hide those that don't match the filter
			for (i = 1; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[4]; // Adjust the index as needed
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>

</body>
</html>