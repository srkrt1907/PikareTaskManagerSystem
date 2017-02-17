<%@ page pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-type" content="text/plain; charset=UTF-8"/>
<title>Insert title here</title>

</head>
<body>
	<div class="form-horizontal">
<!-- 	col-md-6 col-md-offset-3 col-lg-offset-0 -->
		<div class="col-lg-3">
      <div class="well">
      <h3 align="center">Search Filter</h3>
        <form class="form-horizontal">
        
          <div class="form-group">
            <label for="type1" class="control-label">Listeleme Tipi</label>
            <select class="form-control" onchange="selectedChageType()" name="listtype" id="listtype">
             <option value="">--Select One--</option>
              <option value="1">Task Listesine Göre</option>
              <option value="2">Kişi Bazında Sayılara Göre</option>
            </select>
          </div>
        
        <div class="form-group">
            <label for="location1" class="control-label">Hafta</label>
            <select class="form-control" onchange="selectedChageHafta()" name="hafta" id="hafta">
              <option value="">--Select One--</option>
              <c:forEach items="${weekList}" var="week">
						<option value="${week}">${week}</option>
				</c:forEach>
            </select>
          </div>       
          <div class="form-group">
            <label for="location1" class="control-label">Kişi</label>
            <select class="form-control" name="kisi" id="kisi">
             <option value="">--Select One--</option>
              <c:forEach items="${users }" var="user">
				 <option value="${user[0].name}" >${user[0].name}</option>
			  </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="type1" class="control-label">Kategori</label>
            <select class="form-control" name="" id="kategori">
             <option value="">--Select One--</option>
               <c:forEach items="${anakategoriList}" var="kat">
					<option value="${kat}">${kat}</option>
				</c:forEach>
            </select>
          </div>
         
          <div class="form-group">
            <label for="pricefrom" class="control-label">Ilk Tarih</label>
            <div class="input-group">
              <div class="input-group-addon" id="basic-addon1"></div>
              <input type="date" class="form-control" id="firstdate" aria-describedby="basic-addon1">
            </div>
          </div>
          <div class="form-group">
            <label for="priceto" class="control-label">Son Tarih</label>
            <div class="input-group">
              <div class="input-group-addon" id="basic-addon2"></div>
              <input type="date" class="form-control" id="lastdate" aria-describedby="basic-addon1">
            </div>
          </div>
          
          <div class="form-group">
            <label for="priceto" class="control-label">Status</label>
            <div class="input-group">
              <div class="input-group-addon" id="basic-addon2"></div>
              <select class="form-control input-sm" id="status" name="status"> 
							 	    <option value="" label="--Please Select"/>
									<option value="WAITING"  label="WAITING"/>
									<option value="OPEN"  label="OPEN"/>
									<option value="CLOSED"  label="CLOSED"/>						 
			</select>			
            </div>
            <br />
            <p style="color: red">Status Seçilmediginde default olarak kapanan tasklar gelmektedir.</p>
          </div>
          <p class="text-center">
          <input  type="button" value="Search" class="btn btn-danger" id="ara" />
          </p>
        </form>
      </div>
    </div >
    	
    	<div class="col-lg-9" id="datatablesdiv">
<!--     	<div style="display: none" class="panel panel-default" id="panelid" > -->
<!--            <div class="panel-heading" > -->
<!--                            <span id="baslik"></span> -->
<!--              </div> -->
    		<table id="displayTable" class="table responsive table-striped table-bordered table-hover" cellspacing="0"  width="100%"></table>
<!--     		</div> -->
    	</div>
	</div>
	
	<div class="modal"><!-- Place at bottom of page --></div>
	
	
</body>
</html>