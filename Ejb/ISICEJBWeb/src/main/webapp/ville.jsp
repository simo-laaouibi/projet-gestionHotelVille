<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>City Management</title>
    <!-- Add Bootstrap CDN links -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
        <form action="VilleController" method="post" class="form-inline">
            <div class="form-group mb-2">
                <label for="ville" class="mr-2">Nom :</label>
                <input type="text" name="ville" class="form-control mr-2" />
            </div>
            <button type="submit" class="btn btn-primary mb-2">Enregistrer</button>
        </form>

        <!-- Add an input field for Ville filtering above the table -->
       
       

        <!-- Your existing table structure -->
        <h1>Liste des Villes:</h1>
        <table id="villesTable" class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${villes}" var="v">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.nom}</td>
                        <td>
                            <form action="VilleController" method="post" onsubmit="return confirmModification()">
                                <input type="hidden" name="id" value="${v.id}" />
                                <button type="submit" class="btn btn-warning btn-sm" name="action" value="update">Modifier</button>
                            </form>
                        </td>
                        <td>
                           <form action="VilleController" method="post" onsubmit="return confirmDelete()">
                                <input type="hidden" name="id" value="${v.id}" />
                                <button type="submit" class="btn btn-danger btn-sm" name="action" value="delete">Supprimer</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

<script>
    function confirmDelete() {
        return confirm("Êtes-vous sûr de vouloir supprimer cette ville ?");
    }
</script>

	<script>
    function confirmModification() {
        return confirm("Êtes-vous sûr de vouloir Modifier ?");
    }
</script>
    <!-- Add Bootstrap JS and Popper.js CDN links if needed -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-eMNCO9T6m2UU5gAmV8pa0Wu3Fu6arYpCFFmeEdiQf8ANq1ApjTlXUnBh1rL2L+qV" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    

</body>
</html>
