<%@ page pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">İstatislik</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
<%--             			<jsp:include page="basicLine.zul"></jsp:include> --%>
                   		<form method="GET" action="home2" >
                   		
                   		<label class="col col-lg-1"  for="name">Hafta</label> 
                   		<div class = "col-md-2">
						  <select class="form-control input-sm" id="taskWeek" name="taskWeek">						  
						    <option value="" label="--Please Select"/>
							<c:forEach items="${weekList}" var="week">
								<option value="${week}">${week}</option>
							</c:forEach>
							</select>
                   		
 
						  </div>

                   		<label class="col col-lg-1"  for="name">Task Assigment</label>  
						  <div class = "col-md-2">
						  <select class="form-control input-sm" id="taskSahibi" name="taskSahibi">
						  
						   <option value="" label="--Please Select"/>
						   <c:forEach items="${users }" var="user">
						   		<option value="${user[0].name}" label="${user[0].name}"></option>
						   	</c:forEach>
							</select>
                   			</div>
 								

							<label class="col col-lg-1" for="name"> Kategori</label>  
							  <div class="col-md-2">
							 <select class="form-control input-sm" id="anakategori"   name="anakategori"> 
							 	   <option value="" label="--Please Select"/>
							 <c:forEach items="${anakategori }" var="kat">
							   		<option value="${kat}" label="${kat}"></option>
							 </c:forEach>
							</select>
						  </div>
						  
						  <label class="col-md-1 control-label" for="name"> Status</label>  
							  <div class="col-md-2">
							 <select class="form-control input-sm" id="status"   name="status"> 
							 	   <option value="" label="--Please Select"/>
									<option value="WAITING" label="WAITING">
									<option value="OPEN" label="OPEN">
									<option value="CLOSED" label="CLOSED">						 
							</select>
						  </div>
							
												
<!--                    		 <label class="col-md-1 control-label" for="name">Kategori</label>   -->
<!-- 							  <div class="col-md-2"> -->
							  <input type="hidden" name="kategori" value=""/>
<!-- 							 <select class="form-control input-sm" id="kategori" name="kategori">  -->
<!-- 							 	   <option value="" label="--Please Select"/> -->
<%-- 							 <c:forEach items="${kategoriler }" var="kat"> --%>
<%-- 							   		<option value="${kat.kategori}" label="${kat.kategori}"></option> --%>
<%-- 							 </c:forEach> --%>
<!-- 							</select> -->
<!-- 						  </div> -->
							
							<input class="btn" type="submit" value="Listele">
       
                        </form>
							
                   
                </div>
            <!-- /.row -->
            <div class="row">
                  
                    
                    <div class="panel-body">
                    	
<%--                     	<jsp:include page="basicLine.zul"></jsp:include> --%>

      				 <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th style="width: 5%">Task No</th>
                                        <th>Task Adi</th>
                                        <th>Task Kimin</th>
                                        <th style="width: 5%">Aciliyet</th>
                                        <th style="width: 7%">İş Tanımı</th>
                                        <th style="width: 7%">Status</th>
                                        <th>Kategori</th>
                                        <th >Açılış Tar.</th>
                                        <th>Kapanış Tar.</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:forEach items="${Task}" var="task">
                                 <tr class="odd gradeX" >
                                        <td><a href="taskupdate?taskid=${task.taskNo}">${task.taskNo}</a></td>
                                        <td>${task.taskName}</td>
                                        <td>${task.taskSahibi}</td>
                                        <td >${task.acil}</td>
                                        <td >${task.isTanimi}</td>
                                        <td >${task.status}</td>
                                        <td >${task.kategori}</td>
                                        <td >${task.openWeek}</td>
                                        <td >${task.closeWeek}</td>    
                                    </tr>     
   						
								</c:forEach>
                                    
                                </tbody>
                            </table>
                            
      					</div>
    				</div>
                    <!-- /.panel -->
                  
            <!-- /.row -->
</body>

<content tag="local_script">
<script>
 $(document).ready(function() {
// 	alert("yuklendi");
			

			<c:if test="${not empty kategori}">
			var	paramOne ="<c:out value='${kategori}'/>";
				 document.getElementById('kategori').value =paramOne;
			</c:if>

			<c:if test="${not empty taskSahibi}">
			var paramtwo ="<c:out value='${taskSahibi}'/>";
				document.getElementById('taskSahibi').value =paramtwo;
			</c:if>


			<c:if test="${not empty week}">
			var paramthree ="<c:out value='${week}'/>";
			document.getElementById('taskWeek').value =paramthree;
			</c:if>

// 			var paramOne =<c:out value="${kategori}"/>
// 			var paramtwo =<c:out value="${taskSahibi}"/>
// 			var paramthree =<c:out value="${week}"/>

			
			
// 			document.getElementById('kategori').value =paramOne;
			
		    $('#dataTables-example').DataTable({
		        responsive: true
		    });

  }); 
 </script> 
</content>
</html>