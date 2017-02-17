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
                            <table width="100%" class="table table-striped table-bordered" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th >id</th>
                                        <th>Portal İsmi</th>
                                        <th >Sunucu 1</th>
                                        <th >Sunucu 2</th>
                                        <th >Web Servis</th> 
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${wifiList}" var="wifiLis"> 
                                 <tr class="odd gradeX" id="<c:out value='${wifiLis.id}'></c:out>" >                                         
                                        <td >${wifiLis.id}</td>
                                        <td>${wifiLis.name}</td>
                                        <td >${wifiLis.sunucu1}</td>
                                        <td>${wifiLis.sunucu2}</td>
                                        <td >${wifiLis.webServis}</td>
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
										<form:input type="text" path="webServis" required="required"></form:input>
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
	var editor; // use a global for the submit and return data rendering in the examples
	 
// 	    editor = new $.fn.dataTable.Editor( {
	    	
// 	        table: '#dataTables-example',
// 	        fields: [ 
// 	     	         {
// 	                label: "id:",
// 	                name: "id" ,
// 	                type :"hidden",
// 	     	        "default": "0"
	                
// 	            },{
// 	                label: "Name:",
// 	                name: "name",
// 	            }, {
// 	                label: "Sunucu 1:",
// 	                name: "sunucu1",
// 	            }, {
// 	                label: "Sunucu 2:",
// 	                name: "sunucu2",
// 	            }, {
// 	                label: "Web Servis:",
// 	                name: "webServis",
// 	            }
// 	        ]
// 	    } );

	<c:if test="${not empty msg}">
	var	paramOne ="<c:out value='${msg}'/>";
	swal("Sonuç!", paramOne );
	</c:if>
	 
	    $('#dataTables-example').DataTable();


	    
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