/**
 * 
 */
function child_open()
{ 
popupWindow =window.open('display',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=no,width=600, height=350,top=200,left=200");
return false;
}
function deneme()
{
	var strUser = "";
	$.ajax({
    	url: "../data/taskAll",
    	data: "user=" + strUser ,
    	type: "GET",
    	success: function(response) {
			alert("yuklendi");
			config = { 
     	            data: response, 
     	            xkey: '0', 
     	            ykeys: ['1'], 
     	            labels: ['Kapananlar'], 
     	            fillOpacity: 0.6,
     	            hideHover: 'auto', 
     	            behaveLikeLine: true, 
     	            resize: true, 
     	            pointFillColors:['#ffffff'], 
     	            pointStrokeColors: ['black'],
     	            lineColors:['gray','red'] 
     	        }; 
     	      config.element = 'line-chart'; 
     	      Morris.Line(config); 
	    }
	});
	
	}


$('#getir').click(function() {
	var e = document.getElementById("taskSahibi");
	var strUser = e.options[e.selectedIndex].text;
	
	$.ajax({
    	url: "../data/taskAll",
    	data: "user=" + strUser ,
    	type: "GET",
    	success: function(response) {
		
    }});
});




function validateForm()
{	
	var is = document.getElementById("isTanimi").value;
	var task = document.getElementById("taskSahibi").value;
	var statu = document.getElementById("status").value;
	var week = document.getElementById("closeWeek").value;
	var openWeek = document.getElementById("openWeek").value;
	var assignweek =document.getElementById("assigmnetDate").value;
	
	if(task == '' && is != '' )
		{
		sweetAlert("Hata", "Sahibi olmayan taskta efor secilemez!", "error");
		return false;
		}
	
	if(statu == 'CLOSED' && (is == '' || task == '' || week == '') )
			{
			sweetAlert("Hata", "Kapanan taskın kullanıcısı, eforu yada kapanış tarihi bos olamaz. Lutfen doldurunuz", "error");	
			return false;
			}
	
	if(statu == 'WAITING' && (is != '' || task != ''))
	{
		sweetAlert("Hata", "Bekleyen bir taskta efor yada kullanıcı secilemez", "error");
		return false;
	}
	
	if(statu == 'OPEN' && (is != '' || task == ''))
	{
		sweetAlert("Hata", "Acik bir taskta kullanici bos efor dolu olamaz..", "error");
		return false;
	}
	if(document.getElementById("closeWeek").disabled == false)
	{
		if(statu != 'CLOSED' && week != '')
		{
			sweetAlert("Hata", "Acik bir taskta kapaniş tarihi olamaz", "error");
			return false;
			}
	}
		
	
	if(week != '' && (week < openWeek))
		{
		sweetAlert("Hata", "Kapanma tarihi açılış tarihinden önceki bir tarih olamaz", "error");
		return false;
		}
	
	if(assignweek != '' && assignweek < openWeek)
		{
		sweetAlert("Hata", "Atanma tarihi açılış tarihinden önceki bir tarih olamaz", "error");
		return false;
		}
	
	if(week != '' && (week < assignweek))
	{
	sweetAlert("Hata", "Kapanma tarihi Atanma tarihinden önceki bir tarih olamaz", "error");
	return false;
	}
	

	var acil=$("select[name='acil']");
	acil.removeAttr('disabled');
	var isTanimi=$("select[name='isTanimi']");
	isTanimi.removeAttr('disabled');
	var taskSahibi=$("select[name='taskSahibi']");
	taskSahibi.removeAttr('disabled');
	var kategori=$("select[name='kategori2']");
	kategori.removeAttr('disabled');
	
	if(week != '')
		{
		$('#closeWeek').removeAttr('disabled'); 
		}
	
	return true;
}

function validateidenticalForm()
{	
	var pass = document.getElementById("changePass").value;
	var passConfirm = document.getElementById("changePassConfirm").value;
	
	if(pass != passConfirm)
		{
		sweetAlert("Hata", "Şifreler uyuşmamaktadir lütfen kontrol ediniz!!", "error");
		return false;
		}
	
	if(pass.length < 6)
	{
	sweetAlert("Hata", "Şifre en az 6 haneli olmalidir!!", "error");
	return false;
	}
		
	
	return true;
}

function selectedChageHafta()
{
	var e = document.getElementById("hafta");
	var selected = e.options[e.selectedIndex].value;
	
		if(selected != "")
			{
			$("#firstdate").val('');
			$("#lastdate").val('');
			$("#firstdate").attr("disabled", true);
			$("#lastdate").attr("disabled", true);	
			}
		else
			{
			$("#firstdate").attr("disabled", false);
			$("#lastdate").attr("disabled", false);	
			}
	
	
	}

function selectedChageType()
{
	var e = document.getElementById("listtype");
	var selected = e.options[e.selectedIndex].value;
	
		if(selected == "2")
			{
			$("#kisi").val('');
			$("#kisi").attr("disabled", true);
			$("#status").val('');
			$("#status").attr("disabled", true);	
			
			}
		else
			{
			$("#kisi").attr("disabled", false);
			$("#status").attr("disabled", false);	
			}
	}



$("#ara").click(function()
{
	$body = $("body");
	$body.addClass("loading");
	var e = document.getElementById("kisi");
	var strUser = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("hafta");
	var hafta = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("kategori");
	var kategori = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("listtype");
	var type = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("status");
	var status = e.options[e.selectedIndex].value;
	
	var firstdateVal ="";
	var lastdateVal ="";
	if(hafta == ""){
		firstdateVal = $("#firstdate").val();
		lastdateVal = $("#lastdate").val();
	}
	
	if ( $.fn.DataTable.isDataTable('#displayTable') ) {
		  $('#displayTable').DataTable().destroy();
		  $('#displayTable').empty();
		}

		//$('#displayTable tbody').empty();
	
	 //$('#displayTable').dataTable().fnDestroy();
	if(type == "2")
		{
		
		$.ajax({
			url: "../data/filtrele",
		      type: 'GET',
		      data: "kisi=" + strUser + "&hafta=" + hafta + "&kategori=" + kategori + "&firstdate="+firstdateVal+"&lastdate="+lastdateVal,
		    success: function(data){
		    	
		    	$('#baslik').html('Kişi Bazli Sayi Tablosu');
		    	$('#panelid').css('display', 'block');	
	          
//	          var tableHeaders = "<th></th><th>Alınan</th><th>Kapanan</th><th>Tümü</th>";        
	          
		    	var column_names = ['Kişiler','Alınan','Kapanan','Tümü'];
		        var columns = [];
		        for (var i = 0; i < column_names.length; i++) {
		            columns[i] = {
		                'title': column_names[i]
		                
		            }
		        };
	          var dataSet=[];
	          for (var int = 0; int < data.length; int++) {
	           	  var closeTask = data[int].close;
	                 var openTask = data[int].all;
	                 var assignTask = data[int].open;
	                 var name = data[int].name;
	                 var data2 = [name,assignTask,closeTask,openTask];
	                 dataSet.push(data2);
	          }
	          
	          
//	          $("#datatablesdiv").append('<table id="displayTable" class="table responsive table-striped table-bordered table-hover" cellspacing="0"  width="100%">');	
//	          $("#tableDiv").find("table thead tr").append(tableHeaders);
//	          $("#tableDiv").append('<table id="displayTable" class="table table-striped table-bordered" ><thead><tr>' + tableHeaders + '</tr></thead></table>');      
	          $('#displayTable').DataTable({        
	        	  columns: columns,
	        	  data: dataSet,  
	              dom: 'lBfrtip',
	              buttons: [
	                  'copyHtml5',
	                  'excelHtml5',
	                  'pdfHtml5'
	              ]   
	              
	          });      
	          $body.removeClass("loading"); 
	    }
	});
			
}
	else
	{
	$.ajax({
		url: "../data/taskliste",
	      type: 'GET',
	      data: "kisi=" + strUser + "&hafta=" + hafta + "&kategori=" + kategori + "&firstdate="+firstdateVal+"&lastdate="+lastdateVal + "&status="+status,
	    success: function(data){
	    	
	    	$('#baslik').html('Task Listesi');
	    	$('#panelid').css('display', 'block');
	    	
          
//          var tableHeaders = "  <th style='width: 5%'>Task No</th><th>Task Adi</th><th>Task Assigment</th><th style='width: 5%'>Aciliyet</th> "
//          							+"<th style='width: 5%'>İş Tanımı</th><th style='width: 7%'>Status</th>"
//                                   +" <th>Kategori</th> "
//                                    +"<th >Açılış Tar.</th>"
//                                    +"<th>Başlama Tar.</th>"
//                                    +"<th>Kapanış Tar.</th>"
//                                  +"<th>Talep Sahibi</th>"
//                                  +"<th>Talep Yöneticisi</th>";        
          
          
          var column_names = ['Task No','Task Adi','Task Assigment','Aciliyet','İş Tanımı','Kategori','Açılış Tar.','Başlama Tar.','Kapanış Tar.','Talep Sahibi','Talep Yöneticisi'];
	        var columns = [];
	        for (var i = 0; i < column_names.length; i++) {
	            columns[i] = {
	                'title': column_names[i]   
	            }
	        };
          
          
          var dataSet=[];
          for (var int = 0; int < data.length; int++) {
                 var data2 = [data[int].taskNo,data[int].taskName,data[int].taskSahibi,data[int].acil,data[int].isTanimi,data[int].status,data[int].kategori,data[int].openWeek,data[int].assigmnetDate,data[int].closeWeek,data[int].talepSahibi,data[int].yonetici];
                 dataSet.push(data2);
          }
          
//          $("#datatablesdiv").append('<table id="displayTable" class="table responsive table-striped table-bordered table-hover" cellspacing="0"  width="100%">');
//          $("#tableDiv").find("table thead tr").append(tableHeaders);
//          $("#tableDiv").append('<table id="displayTable" class="table table-striped table-bordered"><thead><tr>' + tableHeaders + '</tr></thead></table>');      
          $('#displayTable').DataTable({        
              data: dataSet,
              columns: columns,
              dom: 'lBfrtip',
              buttons: [
                  'copyHtml5',
                  'excelHtml5',
                  'pdfHtml5'
              ],
              columnDefs: [
                           {
                               targets:0,
                               render: function ( data, type, row, meta ) {
                                   if(type === 'display'){
                                	   data = '<a href="taskupdate?taskid=' + encodeURIComponent(data) + '">' + data + '</a>';
                                   }
                                   return data;
                               }
                           }
                       ]  
              
          }); 
          $body.removeClass("loading"); 
      
    }
});
		
	}

	
	
	
});


