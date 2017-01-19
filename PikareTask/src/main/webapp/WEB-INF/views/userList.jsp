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

    <div class="row">
    <fieldset class="for-panel">  		     
          <div class="row">
 
            	<legend>Kullanici Listesi</legend>
             <div class="col-lg-12">              
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th style="width: 5%">username</th>
                                        <th>Roles</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:forEach items="${userList}" var="user">
                                 <tr class="odd gradeX" >
                                        <td><a href="userUpdate?username=${user.username}">${user.username}</a></td>
                                        	<td>
                                        	<c:forEach items="${user.userRole }" var="role">
                                        		${role.role},
                                        	</c:forEach>
                                        	<td>
                                        	
                                    </tr>     
   						
								</c:forEach>
                                    
                                </tbody>
                            </table>  
              </div>
            </div>
          
        </fieldset>
    </div>

</body>

<content tag="local_script">
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
        responsive: true
    });
});
</script>
</content>

</html>
            <!-- /.row -->