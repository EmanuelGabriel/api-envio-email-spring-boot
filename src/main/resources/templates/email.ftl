<html>
    <head>
		<title>Seu link de acesso para alterar a senha</title>
		<meta http-equiv="Content-Language" content="pt-br">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="PRAGMA" content="NO-CACHE">
        <meta name="viewport" content="initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
        <style>
            body{
                background-color: beige;
                font-family: 'Roboto', sans-serif;
            }
           .container{
                display: flex;
                justify-content: center;
                align-items: center;
                
           }
           .child{
                width: 700px;
                background-color: white;
                padding: 3%;
           }
           .child img{
               align-items: center;
           }
           .child h1{
                color: #2a4797;
           }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="child">
                <img rel="preload" height="120px" width="155px"
                    src="https://blog.aspose.com/wp-content/uploads/sites/2/2020/05/Create-and-Send-Outlook-Emails-Java.jpg"
                    alt="Emanuel Gabriel Sousa">
                <h1>Sistema de Envio de E-mails</h1>
                <h2>Olá, ${nome}</h2>
				<p>Você solicitou uma alteração da senha de acesso aos sistemas de envio de e-mail. Clique no link ${url} abaixo para prosseguir com a alteração:</p>

            	<p>Esse link irá expirar em 24 horas</p>
            	<p>Caso você não tenha pedido essa alteração, entre em contato conosco pelo e-mail emanuel.gabriel.sousa@gmail.com</p>
            	<p>Atenciosamente</p>
            	<span></span>
            	<p>Emanuel Gabriel Sousa - Developer Senior Java</p>
            </div>
        </div>
	</body>
</html>