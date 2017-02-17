<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Pikare Task Assigment System</title>

    <!-- Bootstrap -->
    <link href="../resources/admin/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../resources/admin/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../resources/admin/css/nprogress.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../resources/admin/css/custom.min.css" rel="stylesheet">
    
    
    <link href="../resources/admin/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../resources/admin/datatables-responsive/dataTables.responsive.css" rel="stylesheet"> 
    <link href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css" rel="stylesheet">
    
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet" type="text/css">
		<link href="../resources/admin/datatables/css/editor.dataTables.min.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/admin/css/loading.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/admin/datatables/css/select.dataTables.min.css" rel="stylesheet" type="text/css"/>
	
	
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="home" class="site_title"><i class="fa fa-moon-o"></i> <span>Pikare Task</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="../resources/admin/img/user.png" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2><sec:authentication property="principal.username" /></h2>
              </div>
              <div class="clearfix"></div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a href="home"><i class="fa fa-home"></i> Raporlama </span></a>
                  </li>
                  <li><a href="task"><i class="fa fa-edit"></i>All Tasks </span></a>
                  </li>
                   <sec:authorize access="hasRole('PO')">
                         <li>
                            <a href="taskEkle"><i class="fa fa-edit"></i> Add New Task</a>
                        </li>
                         </sec:authorize>
                  <sec:authorize access="hasRole('USER')">
                  <li><a href="myTask"><i class="fa fa-desktop"></i> My Task</span></a>
                  </li>
                  </sec:authorize>
                  
                  <sec:authorize access="hasAnyRole('USER , ADMIN')">
                  <li><a><i class="fa fa-table"></i> Pikare <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="m2m">M2M</a></li>
                      <li><a href="yonetwifi">Yonet Wifi</a></li>
                    </ul>
                  </li>
                  </sec:authorize>
                </ul>
              </div>


            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                   <img src="../resources/admin/img/user.png" alt="" >
					<sec:authentication property="principal.username" />
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                  <sec:authorize access="hasRole('USER')">
                    <li><a href="myTask"> My Tasks</a></li>
                    </sec:authorize>
                    <li><a href="changePassword">Change Password</a></li>
                     <sec:authorize  access="hasRole('ADMIN')">
                    <li><a href="newUser">Add User</a></li>
                    <li><a href="listUser">Edit Users</a></li>
                    </sec:authorize>
                    <li><a href="logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>
			</ul>
                
      
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          	<dec:body />
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            <sec:authentication property="principal.username" />
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>
	
	<script type="text/javascript" src="../resources/admin/js/jquery.min.js"></script>
    <!-- jQuery --><script type="text/javascript" src="../resources/admin/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../resources/admin/js/fastclick.js"></script>
    <script type="text/javascript" src="../resources/admin/js/nprogress.js"></script>
    <script type="text/javascript" src="../resources/admin/js/custom.min.js"></script>
	

    
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
    
	<script type="text/javascript" src="../resources/admin/datatables/js/dataTables.editor.min.js"></script>
	<script type="text/javascript" src="../resources/admin/datatables/js/dataTables.select.min.js"></script>
	
	<dec:getProperty property="page.local_script"></dec:getProperty>
  </body>
</html>
