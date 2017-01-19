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

    <div class="row">
    <fieldset class="for-panel">
    <br>
         <legend>Şifre Degiştir</legend> 
          
          <br>  
    <form id="identicalForm" class="form-horizontal" action="changePass?${_csrf.parameterName}=${_csrf.token}" onsubmit=" return validateidenticalForm();" method="POST">
    <div class="form-group">
        <label class="col-xs-3 control-label">Yeni Parola</label>
        <div class="col-xs-5">
            <input type="password" class="form-control" name="changePass" id="changePass" required/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Yeni Parola(Tekrar)</label>
        <div class="col-xs-5">
            <input type="password" class="form-control" name="changePassConfirm" id="changePassConfirm" required/>
        </div>
    </div>
    
    <div class="form-group" >
    	<button type="submit"  class="btn btn-primary center-block">Kaydet</button>
    </div>
    
    <span id="error" style="color: red" ></span>
<%--     <p  style="color: red"  >${msg}</p> --%>
</form>


        </fieldset>
    </div>

</body>

<content tag="local_script">
<script>

$(document).ready(function() {
<c:if test="${not empty msg}">
var	paramOne ="<c:out value='${msg}'/>";
swal("Sonuç!", paramOne );
</c:if>

});

</script>
</content>


</html>
            <!-- /.row -->