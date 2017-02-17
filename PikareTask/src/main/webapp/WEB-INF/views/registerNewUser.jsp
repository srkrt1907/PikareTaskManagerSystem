<%@ page pageEncoding="UTF-8" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <div class="form-horizontal">
     
            <c:choose>
			    <c:when test="${saveorupdate=='update'}">
			  		<legend>Kullanıcı Guncelle</legend>
			    </c:when>    
			    <c:otherwise>
					<legend>Kullanıcı Ekle</legend>
			    </c:otherwise>
				</c:choose>
              <div class="form-horizontal">               
             	 <form:form method="POST"  modelAttribute="user" action="newUser" >
				     <form:input type="hidden" path="id"  class="form-control input-sm"/>
				    <div class="form-group">
					  <label class="col-md-4 control-label" for="name">username</label>  
					  <div class="col-md-4">
					  	 <form:input type="text" path="username"  class="form-control input-sm" required="required"/>
					  </div>
					</div>
					<div class="form-group">
					  <label class="col-md-4 control-label" for="name">şifre</label>  
					  <div class="col-md-4">
					  	<form:input type="password" path="password" class="form-control input-sm" required="required"/>
					  </div>
					</div>
					<div class="form-group">
					  <label class="col-md-4 control-label" for="name">Aktiflik</label>  
					  <div class="col-md-4">
					  	<form:select path="enabled"  class="form-control input-sm">
				    	<form:option value="true" />
				    	<form:option value="false" />
				    	</form:select>
					  </div>
					</div>
					<div class="form-group">
					  <label class="col-md-4 control-label" for="name">İsim</label>  
					  <div class="col-md-4">
					  <form:input type="text" path="name" class="form-control input-sm" required="required"/>
					  </div>
					</div>
					<div class="form-group">
					  <label class="col-md-4 control-label" for="name">Soy İsim</label>  
					  <div class="col-md-4">
					  <form:input type="text" path="surname" class="form-control input-sm" required="required"/>
					  </div>
					</div>
					<div class="form-group">
					  <label class="col-md-4 control-label" for="name">Role</label>  
					  <div class="col-md-4">
					  	<form:select multiple="true" path="roles"  class="form-control input-sm" required="required">
				    	<form:option value="ROLE_USER" />
				    	<form:option value="ROLE_ADMIN" />
				    	<form:option value="ROLE_PO" />
				    </form:select>
					  </div>
					</div>
				    
				    <div class="form-group">
				    <label class="col-md-4 control-label" for="singlebutton"></label>
    					<div class="col-md-4">
    					 <c:choose>
    				<c:when test="${saveorupdate=='new'}">
				   	 <button type="submit"  class="btn btn-primary center-block">Ekle</button>
				     </c:when>    
			    	<c:otherwise>
			    		<button type="submit"  class="btn btn-primary center-block">Güncelle</button>
			    	</c:otherwise>
			    	</c:choose>
				    </div>
				    </div>
				    <br>
<%-- 				    <p style="color:red">${msg}</p> --%>
					 </form:form>
             	      	
              </div>
    </div>

</body>

<content tag="local_script">
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
        responsive: true
    });

    <c:if test="${not empty msg}">
    var	paramOne ="<c:out value='${msg}'/>";
    swal("Sonuç!", paramOne );
    </c:if>
});
</script>
</content>

</html>
            <!-- /.row -->