<%@ page pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<sec:authorize access="hasRole('ADMIN')">
	
	<div class="form-horizontal">
	<div class="col-lg-6">
	<div class="panel-heading">
           Yeni out dosyası yukle
    </div>
    <form method="POST" action="../data/uploadFile?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
    <input class="form-control input-sm" type="file" name="file" required="required"/><br/>
        <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />  
   					
				  	
    <input class="btn btn-primary center-block" type="submit" value="Dosya Yükle" />
    </form>  
    </div>                                
   </div>
   </sec:authorize>
   
   <div class="form-horizontal">
   <div class="col-lg-6">
	<div class="panel-heading">
           ICCID excel yükle
    </div>
    <form  enctype="multipart/form-data">
    <input class="form-control input-sm" type="file" name="fileICCID" id="fileICCID" required="required"/><br/>
    
		
	 <input type="hidden" name="_csrf" id="_csrf"
				value="${_csrf.token}" />			
	<input type="hidden" name="_csrf_header" id="_csrf_header"
				value="${_csrf.headerName}" />				
    <input class="btn btn-primary center-block" type="button" value="Baş Ve Bitiş Ara" id="uploadAndFile"/>
    </form> 
    </div>                                 
   </div>
   
   <div class="form-horizontal">
   <div class="col-lg-6"> 
   	<table class="table responsive table-striped table-bordered table-hover" id="tableSonuc"></table>
   	</div>
	</div>

<div class="modal"><!-- Place at bottom of page --></div>
</body>

<content tag="local_script">
<script>
<c:if test="${not empty msg}">
var	paramOne ="<c:out value='${msg}'/>";
swal("Sonuç!", paramOne );
</c:if>
</script>
</content>

</html>
            <!-- /.row -->