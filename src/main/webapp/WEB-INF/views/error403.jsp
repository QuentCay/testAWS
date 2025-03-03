<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accès Interdit - 403</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }

        h1 {
            font-size: 3em;
            color: red;
        }

        p {
            font-size: 1.5em;
        }

        img {
            max-width: 100%;
            height: auto;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Accès Interdit - 403</h1>
        <p><strong>You shall not pass!</strong></p>
        <img src="https://http.cat/images/403.jpg"
			alt="403" />
        <p>Vous n'avez pas l'autorisation d'accéder à cette page.</p>
        <a href="${pageContext.request.contextPath}/${slug}">Retour à l'accueil</a>
    </div>
</body>
</html>
