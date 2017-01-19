<%@ page pageEncoding="UTF-8" %>
<!-- All the files that are required -->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div class = "row">
	<div class="col-md-8"  style="font-size:5px;">
		<table class="table" border="1" >

           <h4>Efor List</h4>
            <tr>
                <th>Kategori</th>
                <th>Harf Karşılığı</th>
                <th>Harf Eforu</th>
            </tr>
            <c:forEach var="kat" items="${kategoriler}">
                <tr>
                    <td><c:out value="${kat.kategori} " /></td>
                    <td><c:out value="${kat.eforHarf}" /></td>
                    <td><c:out value="${kat.efordeger}" /></td>
                </tr>
            </c:forEach>
        </table>				
	</div>
</div>
</body>
</html>