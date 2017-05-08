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



<div class="form-horizontal">
                <div class="col-lg-12">
                    <h1>Tüm Tasklar</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Task Table
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th style="width: 5%">Task No</th>
                                        <th>Task Adi</th>
                                        <th style="width: 5%">Task Assigment</th>
                                        <th style="width: 5%">Aciliyet</th>
                                        <th style="width: 5%">Priority</th>
                                        <th>Kategori</th>
                                        <th style="width: 5%">İş Tanımı</th>
                                        <th style="width: 5%">Status</th>
                                        <th style="width: 10%">Açılış Tar.</th>
                                        <th style="width: 10%">Kapanış Tar.</th>
                                        <th style="display: none">Talep Sahibi</th>
                                        <th style="display: none">Yonetici</th>
                                        <sec:authorize access="hasRole('PO')">
                                        <th></th>
										</sec:authorize>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:forEach items="${Task}" var="task">
<%--                                  		 <c:choose> --%>
<%--                                  		 	<c:when test="${task.acil == 'ACIL' && task.status != 'CLOSED'}"> --%>
<!--                                  		 		<tr bgcolor="#FF0000" style="color: black"> -->
<%--                                  		 	</c:when> --%>
<%--                                  		 	<c:otherwise> --%>
<!--                                  		 		<tr> -->
<%--                                  		 	</c:otherwise> --%>
<%--                                  		 </c:choose> --%>
                                 		
                                       <tr>
                                        <td><a href="taskupdate?taskid=${task.taskNo}">${task.taskNo}</a></td>
                                        <td>${task.taskName}</td>
                                        <td>${task.taskSahibi}</td>
                                        <td >${task.acil}</td>
                                        <td>${task.priority}</td>
                                        <td >${task.kategori}</td>
                                        <td >${task.isTanimi}</td>
                                        <td >${task.status}</td>
                                        <td >${task.openWeek}</td>
                                        <td >${task.closeWeek}</td>
                                        <td style="display: none" >${task.talepSahibi}</td>
                                        <td style="display: none">${task.yonetici}</td>
                                        
                                        <sec:authorize access="hasRole('PO')">
                                        	<td><a id="edit-btn" href="./kopyala?id=${task.taskNo}" class="btn btn-info" style="padding-top: 0px;padding-bottom: 0px">Kopyala</a></td>
										</sec:authorize>
                                          
                                    </tr>     
   						
								</c:forEach>
                                    
                                </tbody>
                            </table>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <p style="color:red">**Düzenlemek istediğiniz Task'in üzerine tıklayınız</p>
                    <p style="color:red">**Acil ve kapanmamıs tasklar kırmızı olarak gösterilecektir.</p>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
           
          

</body>

<content tag="local_script">
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
        responsive: true,
        dom: 'lBfrtip',
        "order": [[ 0, "desc" ]],
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'pdfHtml5'
        ],
        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            if ( aData[3] == "ACIL" && aData[7] != "CLOSED" )
            {
            	// $(nRow).addClass( 'important' );
                $('td', nRow).css('background-color', '#FF0038');//css('background-color', 'Red');
                $('td', nRow).css('color', 'white');
            }
        },
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
    
    <c:if test="${not empty msg}">
    var	paramOne ="<c:out value='${msg}'/>";   
    swal("Sonuç!", paramOne );
    </c:if>

    
});
</script>
</content>

</html>
            <!-- /.row -->