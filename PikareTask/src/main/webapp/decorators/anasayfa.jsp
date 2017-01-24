<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pikare Task Assigment System</title>

    <!-- Bootstrap Core CSS -->
    <link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../resources/vendor/morrisjs/morris.css" rel="stylesheet">
	
	 <link href="../resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.10/c3.min.css">  
    <!-- Custom CSS -->
    <link href="../resoruces/dist/css/sb-admin-2.css" rel="stylesheet">

	<style type="text/css">
.field-error .control-label,
.field-error .help-block,
.field-error .form-control-feedback {
    color: #ff0039;
}

.field-success .control-label,
.field-success .help-block,
.field-success .form-control-feedback {
    color: #2780e3;
}
</style>	
</head>

<body >

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home">Pikare Task</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
              
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                    	<sec:authorize access="hasRole('USER')">
                        <li><a href="myTask"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        </sec:authorize>
                        <li><a href="changePassword"><i class="fa fa-user fa-fw"></i> Chage Password</a>
                        </li>
                        <sec:authorize  access="hasRole('ADMIN')">
                        <li><a href="newUser"><i class="fa fa-gear fa-fw"></i>Add User</a>
                        </li>
                        <li><a href="listUser"><i class="fa fa-gear fa-fw"></i>All User/ Edit User</a>
                        </li>
                         </sec:authorize>
                        <li class="divider"></li>
                        <li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
          </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="home"><i class="fa fa-dashboard fa-fw"></i> Raporlama</a>
                        </li>
                        <li>
                            <a href="task"><i class="fa fa-table fa-fw"></i> All Task</a>
                        </li>
                        <sec:authorize access="hasRole('PO')">
                         <li>
                            <a href="taskEkle"><i class="fa fa-edit fa-fw"></i> Add New Task</a>
                        </li>
                         </sec:authorize>
                         <sec:authorize access="hasRole('USER')">
                          <li>
                            <a href="myTask"><i class="fa fa-edit fa-fw"></i> My Tasks</a>
                        </li>
                        </sec:authorize>
                        
                       
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
           	<dec:body />
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../resources/vendor/raphael/raphael.min.js"></script>
    <script src="../resources/vendor/morrisjs/morris.min.js"></script>
    <script src="../resources/data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../resources/dist/js/sb-admin-2.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="../resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="../resources/vendor/datatables-responsive/dataTables.responsive.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
    <script src="../resources/assets/js/task.js"></script>
    
    <script src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script src="//cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>
    
   	
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js"></script>
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.min.js"></script>
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	
	
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>	
	
	
	<dec:getProperty property="page.local_script"></dec:getProperty>
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->

    



</body>



</html>
