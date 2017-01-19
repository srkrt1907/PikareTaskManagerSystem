<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
	  <meta charset="utf-8">
	  </head>

  
	  
<body>
<br>
<form:form id="taskForm" class="form-horizontal" method="POST" action="taskEkle" onsubmit=" return validateForm();" acceptCharset="utf-8" modelAttribute="task">
<fieldset>

<sec:authorize access="hasRole('USER')" var="isUser" />
<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
<sec:authorize access="hasRole('PO')" var="isPo" />



<br>
<!-- Form Name -->

<c:choose>
    <c:when test="${saveorupdate=='update'}">
  		<legend>Task Guncelle</legend>
    </c:when>    
    <c:otherwise>
		<legend>Task Ekle</legend>
    </c:otherwise>
	</c:choose>

<form:input  path="id" type="hidden" />
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Task ID</label>  
  <div class="col-md-4">
  <form:input path="taskNo" name="taskNo" type="text" placeholder="Task İd giriniz" readonly="${!isPo}"  class="form-control input-sm" required="required" /> 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name" >Task Name</label>  
  <div class="col-md-4">
  <form:input path="taskName" name="taskName" type="text" placeholder="Task Adini giriniz" readonly="${!isPo}" class="form-control input-sm" required="required" /> 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name">Task Assigment</label>  
  <div class="col-md-4">
  

  <form:select class="form-control input-sm" path="taskSahibi" disabled="${!isAdmin}" name="taskSahibi" id="taskSahibi">
   <form:option value="" />
   	<c:forEach items="${users }" var="user">
   		<form:option value="${user[0].name}"></form:option>
   	</c:forEach>
   	
   	
</form:select>
</div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name">İş Tanımı</label>  
  <div class="col-md-4">
  <form:select class="form-control input-sm" path="isTanimi" disabled="${!isUser}" itemValue="isTanimi" name="isTanimi" id="isTanimi">
   <form:option value="" />
   
   <c:forEach items="${eforList }" var="efor">
   		<form:option value="${efor}"></form:option>
   	</c:forEach>
	
  </form:select>

  <sec:authorize access="hasRole('USER')">
  <button type="button" onclick="child_open()" style="float: left" class="glyphicon glyphicon-info-sign"></button>
  </sec:authorize>

  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name" >Aciliyet</label>  
  <div class="col-md-4">
  <form:select class="form-control input-sm" path="acil" disabled="${!isPo}" itemValue="acil" name="acil" id="acil" >
   <form:option value="NORMAL"/>
   <form:option value="ACIL"/>
   
  </form:select>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name">Status</label>  
  <div class="col-md-4">
  <form:select class="form-control input-sm" path="status" id="status" itemValue="status" >
   <form:option value="WAITING" />
   <form:option value="OPEN"/>
   <form:option value="CLOSED"/>
  </form:select>
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="name"  >Acılış Tarihi</label>  
  <div class="col-md-4">
  <form:input path="openWeek" type="date" placeholder="Açılış Haftasi" readonly="${!isPo}" class="form-control input-sm" required=""/> 
  </div>
</div>
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Kapanış Tarihi</label>  
  <div class="col-md-4">
  <form:input path="closeWeek" type="date" placeholder="Kapanış Haftasi" disabled="${!isUser}" class="form-control input-sm" name="closeWeek" required=""/> 
  </div>
</div>

<div class="form-group">
<label class="col-md-4 control-label" for="name">Priority</label> 
  <div class="col-md-4">
  <form:input path="priority" type="text" placeholder="Priority" readonly="${!isPo}" class="form-control input-sm" required="required"/> 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name">Talep Sahibi</label>  
  <div class="col-md-4">
  <form:input path="talepSahibi" name="talepSahibi" type="text" placeholder="Talep Sahibi" readonly="${!isPo}" class="form-control input-sm" required="required"/> 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="name">Talep Yöneticisi</label>  
  <div class="col-md-4">
  <form:input path="yonetici" name="yonetici" type="text" placeholder="Yönetici" readonly="${!isPo}" class="form-control input-sm" required="required"/> 
  </div>
</div>

<!-- <div class="form-group"> -->
<!--   <label class="col-md-4 control-label" for="sel1">Select list:</label> -->
<!--   <div class="col-md-4"> -->
<!--   <select class="form-control input-sm" id="sel1"> -->
<!--     <option>1</option> -->
<!--     <option>2</option> -->
<!--     <option>3</option> -->
<!--     <option>4</option> -->
<!--   </select> -->
<!--   </div> -->
<!-- </div> -->

<div class="form-group">
  <label class="col-md-4 control-label" for="name">Kategori</label>  
  <div class="col-md-4">
  <form:select class="form-control input-sm" id="country" itemValue="kategori" path="kategori"> 
   <c:forEach items="${kategori }" var="kat">
   		<form:option value="${kat.kategori}"></form:option>
   	</c:forEach>
</form:select>
</div>
</div>

<div class="form-group">								
		<div class="col-md-16" >
		
	<c:choose>
    <c:when test="${saveorupdate=='update'}">
       <button type="submit" style="width: %50" class="btn btn-primary center-block">Güncelle</button> 
        <br />
    </c:when>    
    <c:otherwise>
    	<button type="submit" style="width: %50" class="btn btn-primary center-block">Ekle</button>
        
        <br />
    </c:otherwise>
	</c:choose>		
		</div>
		</div>
	<br>
<%-- 	<p style="color:red">${msg}</p> --%>
</fieldset>
</form:form>
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