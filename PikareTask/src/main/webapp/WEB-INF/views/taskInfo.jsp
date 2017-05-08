<%@ page pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="no" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<link href="../resources/admin/css/bootstrap.min.css" rel="stylesheet">
 <link href="../resources/admin/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../resources/admin/css/nprogress.css" rel="stylesheet">

<link href="../resources/admin/css/custom.min.css" rel="stylesheet">
  <link href="../resources/admin/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../resources/admin/datatables-responsive/dataTables.responsive.css" rel="stylesheet"> 
    <link href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css" rel="stylesheet">

	<link href="../resources/admin/css/loading.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/admin/datatables/css/select.dataTables.min.css" rel="stylesheet" type="text/css"/>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="../resources/admin/js/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/admin/datatables/js/dataTables.select.min.js"></script>
	
	
	 <!-- DataTables JavaScript -->
    <script src="../resources/admin/datatables/js/jquery.dataTables.min.js"></script>
    <script src="../resources/admin/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="../resources/admin/datatables-responsive/dataTables.responsive.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
    <script src="../resources/admin/js/task.js"></script>
    
    <script src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script src="//cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js"></script>
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.min.js"></script>
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    
</head>
<body style="background-color: white">

<div class="form-horizontal">
 
            </div>
            <!-- /.row -->
            <div class="row" style="margin-right: 15px;margin-left: 15px">
            

                            <h1>Info List</h1>
<!--                         /.panel-heading -->
                        
                            <table width="100%" class="table table-striped table-bordered" id="taskInfotable">
                                <thead>
                                    <tr>
                                        <th>Info Name</th>
                                        <th >Info ID</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${taskInfoList}" var="taskInfo">     
                                	<tr>                                   
                                        <td contenteditable='true' >${taskInfo.infoName}</td>
                                        <td contenteditable='true'>${taskInfo.infoId}</td>
                                        <td><button id="edit-btn" onclick="editTable(${taskInfo.taskNo},${taskInfo.id},this)" class="btn" style="padding-top: 0px;padding-bottom: 0px">Edit</button></td>
    									<td><button id="edit-btn" onclick="deleteTable(${taskInfo.taskNo},${taskInfo.id},this)" class="btn btn-info" style="padding-top: 0px;padding-bottom: 0px">Delete</button></td>
    									                                    
	                                    </tr>     
   							
									</c:forEach>                            
                                </tbody>
                               
                            </table>
                            
                        
<!--                         /.panel-body -->
                    </div>
<!--                     <p style="color:red">**Düzenlemek istediğinizin üzerine tıklayıp Edit'e basiniz</p> -->
                  

						<a id="modal_trigger" class="btn">Yeni Ekle</a>
						
						<div id="modal" class="popupContainer" style="display:none;">
						<header class="popupHeader">
						<span class="header_title">Yeni Task Info Ekle</span>
						<span class="modal_close" id="kapat"><i class="fa fa-times">		
						</i></span>
						</header>
						<section class="popupBody">
						<!-- Username & Password Login form -->
						<div class="user_login">
								<form:form modelAttribute="taskInfo" method="POST" action="taskInfo">
								<div style="display: none;">
										<label>id</label>
										<form:input type="text" path="id"/>
										<br />
										</div>
										
										<div style="display: none;">
										<label>id</label>
										<form:input type="text" path="taskNo" />
										<br />
										</div>
								
										<label>Info Name</label>
										<form:input type="text" path="infoName" required="required"></form:input>
										<br />

										<label>Info ID</label>
										<form:input type="text" path="infoId" required="required"></form:input>
										<br />
										
										<div class="action_btns">		
												<div class="one_half last"><button type="submit" class="btn btn_red">Ekle</button></div>
										</div>
								</form:form>

						</div>

					</section>
            
            </div>
<!--             /.row -->
            
           
          

</body>

<content tag="local_script">
<script>
$(document).ready(function() {
	
	<c:if test="${not empty msg}">
	var	paramOne ="<c:out value='${msg}'/>";
	swal("Sonuç!", paramOne );
	</c:if>
	 

	    
		$("#modal_trigger").click(function() {
			$("#modal").css("display","block");
		});

	    $("#kapat").click(function() {
			$("#modal").css("display","none");
		});

		

});
	 

</script>
</content>

</html>
            <!-- /.row -->