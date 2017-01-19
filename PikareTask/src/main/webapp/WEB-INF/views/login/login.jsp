<%@ page pageEncoding="UTF-8" %>
<!-- All the files that are required -->
<html>

<head>
<meta name="decorator" content="no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-9">
<script
  src="https://code.jquery.com/jquery-3.1.1.js"
  integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
  crossorigin="anonymous"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="resources/assets/css/login.css" rel='stylesheet' type='text/css'>
</head>
<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<body>
<div class = "container">
	<div class="wrapper">
	
		
	
		<form action="login" method="post" name="Login_Form" class="form-signin">       
		    <h3 class="form-signin-heading">Hoş Geldiniz</h3>
			  <hr class="colorgraph"><br>	  
			  <input type="text" class="form-control" name="username" placeholder="Username" required="" autofocus="" />
			  <br>
			  <input type="password" class="form-control" name="password" placeholder="Password" required=""/>     		  
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>
			  <br>
			  <p style="color:red">${msg}</p>
			  <p style="color:red">${error}</p>
			  
			  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />  			
		</form>			
	</div>
</div>
</body>
</html>