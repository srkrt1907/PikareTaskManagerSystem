<%@ page pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">My Task</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Open Task
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body" >
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th style="width: 15%">Task No</th>
                                        <th>Task Adi</th>
                                        <th style="width: 15%">Status</th>
                                        <th style="width: 15%">Açılış Tar.</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:forEach items="${openTask}" var="task">
                                 <tr class="odd gradeX">
                                        <td><a href="taskupdate?taskid=${task.taskNo}">${task.taskNo}</a></td>
                                        <td>${task.taskName}</td>
                                        <td >${task.status}</td>
                                        <td >${task.openWeek}</td>                                     
                                    </tr>     
   						
								</c:forEach>
                                    
                                </tbody>
                            </table>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <p style="color:red">**Düzenlemek istediğiniz Task'in üzerine tıklayınız</p>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Closed Task
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body" >
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example2">
                                <thead>
                                    <tr>
                                        <th style="width: 10%">Task No</th>
                                        <th>Task Adi</th>
                                        <th style="width: 10%">İş Tanımı</th>
                                        <th style="width: 10%">Status</th>
                                        <th style="width: 10%">Açılış Tar.</th>
                                        <th style="width: 10%">Kapanış Tar.</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:forEach items="${closedTask}" var="task">
                                 <tr class="odd gradeX" >
                                        <td><a href="taskupdate?taskid=${task.taskNo}">${task.taskNo}</a></td>
                                        <td>${task.taskName}</td>
                                        <td >${task.isTanimi}</td>
                                        <td >${task.status}</td>
                                        <td >${task.openWeek}</td>
                                        <td >${task.closeWeek}</td>                                  
                                    </tr>     
   						
								</c:forEach>
                                    
                                </tbody>
                            </table>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
          

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
        ]
    });
    $('#dataTables-example2').DataTable({
        responsive: true,
        dom: 'lBfrtip',
        "order": [[ 0, "desc" ]],
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'pdfHtml5'
        ]
    });

   
});
</script>
</content>

</html>
            <!-- /.row -->