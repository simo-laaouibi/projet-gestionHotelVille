<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            color: #495057;
        }

        .container {
            margin-top: 50px;
        }

        .jumbotron {
            background: linear-gradient(to right, #6dd5ed, #6f98fd);
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            color: #fff;
        }

        h1 {
            color: #e0448d;
        }

        .card {
            margin-top: 20px;
        }

        .btn-primary {
            background-color: #6dd5ed;
            border-color: #6dd5ed;
        }

        .btn-primary:hover {
            background-color: #6f98fd;
            border-color: #6f98fd;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="jumbotron text-center">
            <h1 class="display-4">Bienvenue sur notre site</h1>
            <p class="lead">Explorez et gérez les villes et les hôtels</p>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title">Gestion des Villes</h2>
                        <p class="card-text">Accédez à la gestion des villes pour ajouter, modifier ou supprimer des villes.</p>
                        <a href="VilleController" class="btn btn-primary">Gestion des Villes</a>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title">Gestion des Hôtels</h2>
                        <p class="card-text">Explorez la gestion des hôtels pour gérer les informations sur les hôtels disponibles.</p>
                        <a href="HotelController" class="btn btn-primary">Gestion des Hôtels</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
