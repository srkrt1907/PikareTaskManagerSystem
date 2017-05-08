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
 
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                            <h1>Yonet Wifi List</h1>
<!--                         /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered" id="wifiTable">
                                <thead>
                                    <tr>
                                        <th >id</th>
                                        <th>Portal İsmi</th>
                                        <th >Sunucu 1</th>
                                        <th >Sunucu 2</th>
                                        <th >Web Servis</th> 
                                        <th>User</th>
                                        <th>Type</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${wifiList}" var="wifiLis"> 
                                		
                                		<tr class="odd gradeX" id="<c:out value='${wifiLis.id}'></c:out>" >                                         
                                        <td >${wifiLis.id}</td>
                                        <td contenteditable='true'>${wifiLis.name}</td>
                                        <td contenteditable='true'>${wifiLis.sunucu1}</td>
                                        <td contenteditable='true'>${wifiLis.sunucu2}</td>
                                        <td contenteditable='true'>${wifiLis.webServis}</td>
                                        <td contenteditable='true'>${wifiLis.user}</td>
                                        <td contenteditable='true'>${wifiLis.type}</td>
                                        <td><button id="edit-btn" onclick="editWifiTable(this)" class="btn" style="padding-top: 0px;padding-bottom: 0px">Edit</button></td>
    									<td><button id="edit-btn" onclick="deleteWifiTable(this)" class="btn btn-info" style="padding-top: 0px;padding-bottom: 0px">Delete</button></td>
    									  
                                    </tr>     
   							
									</c:forEach>                            
                                </tbody>
                               
                            </table>
                            
                        </div>
<!--                         /.panel-body -->
                    </div>
                    <p style="color:red">**Düzenlemek istediğinizin üzerine tıklayıp Edit'e basiniz</p>
                  

						<a id="modal_trigger" class="btn">Yeni Ekle</a>
						
						<div id="modal" class="popupContainer" style="display:none;">
						<header class="popupHeader">
						<span class="header_title">Yeni Portal Ekle</span>
						<span class="modal_close" id="kapat"><i class="fa fa-times">		
						</i></span>
						</header>
						<section class="popupBody">
						<!-- Username & Password Login form -->
						<div class="user_login">
								<form:form modelAttribute="yonetwifi" method="POST" action="yonetwifi">
								<div style="display: none;">
										<label>id</label>
										<form:input type="text" path="id"/>
										<br />
										</div>
								
										<label>Portal Ismı</label>
										<form:input type="text" path="name" required="required" value=""></form:input>
										<br />

										<label>Sunucu 1</label>
										<form:input type="text" path="sunucu1" required="required"></form:input>
										<br />
										
										<label>Sunucu 2 </label>
										<form:input type="text" path="sunucu2" required="required"></form:input>
										<br />
										
										<label>Web Servis</label>
										<form:input type="text" path="webServis"></form:input>
										<br />
										
										<label>User</label>
										<form:input type="text" path="user"></form:input>
										<br />
										
										<label>Type</label>
										<form:input type="text" path="type"></form:input>
										<br />

										<div class="action_btns">		
												<div class="one_half last"><button type="submit" class="btn btn_red">Ekle</button></div>
										</div>
								</form:form>

						</div>

					</section>
				</div>
						
                </div>
            
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
	 
	  $('#wifiTable').DataTable({
	        responsive: true,
	        dom: 'lBfrtip',
	        "order": [[ 0, "desc" ]],
	        buttons: [
	            'copyHtml5',
	            'excelHtml5',
	            'pdfHtml5'
	        ],
	        "pageLength": 10,
		  	"language": {
			    "search": "Filtre:",
			    	"paginate": {
			            "first":      "İlk",
			            "last":       "Son",
			            "next":       "Sonraki",
			            "previous":   "Önceki"
			        },
			        "lengthMenu":     "_MENU_ adet kayıt görüntüle",
			        "info":           "_TOTAL_ kayıttan  _START_ - _END_ arası gösteriliyor",
			  }
	    });


	    
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