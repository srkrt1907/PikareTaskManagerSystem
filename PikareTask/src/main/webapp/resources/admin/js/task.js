/**
 * 
 */
function child_open()
{ 
popupWindow =window.open('display',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=no,width=600, height=350,top=200,left=200");
return false;
}
function info_open(taskNo)
{ 
popupWindow =window.open('taskInfo?id='+taskNo+'',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=no,width=900, height=500,top=200,left=200");
return false;
}

function force_logout() {
	$.ajax({
		url: "../secure/logout",
	      type: 'GET'
	});
}

function okundusay() {
	$.ajax({
		url: "../secure/okundu",
	      type: 'GET',
	      success: function(response) {	    	  
	    	  $("#notify").html('');
	    	  $("#notify1").html('');
	    	  $("#sayi").html('0');
	    	  
	      }
	});
	
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


$("#uploadAndFile").click(function()
{
	var fd = new FormData();
	var dt = $("#fileICCID");
	var dtt = dt[0].files;
	
	if(dtt[0] == null)
		{
			sweetAlert("Hata", "Bu işlemi yapmak için lütfen dosya seçiniz", "error");
			return false;
		}
	
	var ext = dtt[0].name.split('.').pop();
	if(ext != "xlsx" && ext != "xls")
		{
		sweetAlert("Hata", "Bu işlemi yapmak için lütfen excel dosyasi seçiniz", "error");
		return false;
		}
		
	
	fd.append( "file", dtt[0]);
	
	var token = $("#_csrf").val();
	var header =$("#_csrf_header").val(); 
	$body = $("body");
	$body.addClass("loading");
	if ( $.fn.DataTable.isDataTable('#tableSonuc') ) {
		  $('#tableSonuc').DataTable().destroy();
		  $('#tableSonuc').empty();
		}
	
	 $.ajax({
	                type: "POST",
	                url: "../data/uploadFileAndFind",
	                data: fd,
	                contentType: false,
	                processData: false,
	                cache: false,
//	                beforeSend: function(xhr) {
//	                    // here it is
//	                    xhr.setRequestHeader(header, token);
//	                },
//	                /*beforeSend: function(xhr, settings) {
//	                    xhr.setRequestHeader("Content-Type", "multipart/form-data;boundary=gc0p4Jq0M2Yt08jU534c0p");
//	                    settings.data = {name: "file", file: inputElement.files[0]};                    
//	                },*/
	                success: function (result) {                        
	                    if ( result.length > 0 ) {
	                    	
	                    	var column_names = ['Başlangiç','Bitiş','Sonuc'];
	        		        var columns = [];
	        		        for (var i = 0; i < column_names.length; i++) {
	        		            columns[i] = {
	        		                'title': column_names[i]
	        		                
	        		            }
	        		        };
	        	          var dataSet=[];
	        	          for (var int = 0; int < result.length; int++) {
	        	           	  var bas = result[int].Bas;
	        	                 var bit = result[int].Bit;
	        	                 var sonuc = result[int].Sonuc;
	        	                 var data2 = [bas,bit,sonuc];
	        	                 dataSet.push(data2);
	        	          }
	        	                  	          
	        	          $('#tableSonuc').DataTable({        
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
	        	          sweetAlert("Sonuc", "Sonuc Tabloya Eklendi", "success");
	        	          
	                    } else {
	                    	sweetAlert("Sonuc", "Başlangiç ve Bitiş Bulunamadi", "error");
	                    	 $body.removeClass("loading"); 
	                    }
	                },
	                error: function (result) {
	                    console.log(result.responseText);
	                }
	 });
	
});

function editTable(taskNo,id,edit)
{
	var table = document.getElementById("taskInfotable");
	var row = table.rows[edit.parentNode.parentNode.rowIndex];
	var info = row.cells[0].innerHTML;
	var infoid = row.cells[1].innerHTML;
	 
	$.ajax({
    	url: "../data/updateTaskInfo",
    	data: "id=" + id +"&taskInfo=" + info +"&taskId=" + infoid +"&taskNo=" + taskNo ,
    	type: "GET",
    	success: function(response) {
    		if(response == '1')
    			sweetAlert("Başarılı", "İşlem Başarılı şekilde gercekleşti.", "info");
    		else
    			sweetAlert("Hata", "İşlem yapılırken hata gerçekleşti.", "error");
    }});
	
	
}
function deleteTable(taskNo,id,edit)
{
	swal({
		  title: "Emin misin?",
		  text: "Listeden Silinecek, Yapmak Istediginizden emin misin!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Evet, Sil!"
		},
		function(){
				var table = document.getElementById("taskInfotable");
				var row = table.rows[edit.parentNode.parentNode.rowIndex];
				var info = row.cells[0].innerHTML;
				var infoid = row.cells[1].innerHTML;
				 
				$.ajax({
			    	url: "../data/deleteTaskInfo",
			    	data: "id=" + id +"&taskInfo=" + info +"&taskId=" + infoid +"&taskNo=" + taskNo ,
			    	type: "GET",
			    	success: function(response) {
			    		if(response == '1')
			    			{
			    				sweetAlert("Başarılı", "İşlem Başarılı şekilde gercekleşti.", "info");
			    				row.remove();
			    			}
			    		else
			    			sweetAlert("Hata", "İşlem yapılırken hata gerçekleşti.", "error");
			    }});
		});
	
}
function editWifiTable(edit)
{
	var table = document.getElementById("wifiTable");
	var row = table.rows[edit.parentNode.parentNode.rowIndex];
	var id = row.cells[0].innerHTML;
	var name = row.cells[1].innerHTML;
	var sunucu1 = row.cells[2].innerHTML;
	var sunucu2 = row.cells[3].innerHTML;
	var webServis = row.cells[4].innerHTML;
	var user = row.cells[5].innerHTML;
	var type = row.cells[6].innerHTML;
	 
	$.ajax({
    	url: "../data/updateYonetWifi",
    	data: "id=" + id +"&name=" + name +"&sunucu1=" + sunucu1 +"&sunucu2=" + sunucu2 +"&webServis=" + webServis +"&user="+ user + "&type="+type ,
    	type: "GET",
    	success: function(response) {
    		if(response == '1')
    			sweetAlert("Başarılı", "İşlem Başarılı şekilde gercekleşti.", "info");
    		else
    			sweetAlert("Hata", "İşlem yapılırken hata gerçekleşti.", "error");
    }});
	
	
}
function deleteWifiTable(edit)
{
	swal({
		  title: "Emin misin?",
		  text: "Listeden Silinecek, Yapmak Istediginizden emin misin!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Evet, Sil!"
		},
		function(){
			var table = document.getElementById("wifiTable");
			var row = table.rows[edit.parentNode.parentNode.rowIndex];
			var id = row.cells[0].innerHTML;
			var name = row.cells[1].innerHTML;
			var sunucu1 = row.cells[2].innerHTML;
			var sunucu2 = row.cells[3].innerHTML;
			var webServis = row.cells[4].innerHTML;
			var user = row.cells[5].innerHTML;
			var type = row.cells[6].innerHTML;
			 
			$.ajax({
		    	url: "../data/deleteYonetWifi",
		    	data: "id=" + id +"&name=" + name +"&sunucu1=" + sunucu1 +"&sunucu2=" + sunucu2 +"&webServis=" + webServis  +"&user="+ user + "&type="+type,
		    	type: "GET",
		    	success: function(response) {
		    		if(response == '1')
		    		{
		    			sweetAlert("Başarılı", "İşlem Başarılı şekilde gercekleşti.", "info");
		    			row.remove();
		    		}
		    		else
		    			sweetAlert("Hata", "İşlem yapılırken hata gerçekleşti.", "error");
		    }});
		  
		});
	
	
	
	
	
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
	          
		    		

		    	var column_names = ['Kişiler','Alınan','Kapanan','Pending','Üzerindeki Açık Tasklar(Not pending)' , 'Efor'];
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
	                 var pending = data[int].pending;
	                 var efor = data[int].efor;
	                 var data2 = [name,assignTask,closeTask,pending,openTask,efor];
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
	    	
	    	
	    	if(hafta != '')
	    		{
	    		var DAY = 86400000;
	    		var dateData = hafta.split('-');
		    	
	    		  var year = new Date(dateData[0].toString()); // toString first so it parses correctly year numbers
	    		  var daysToFriday = (5 - year.getDay()); // Note that this can be also negative
	    		  var fridayOfFirstWeek = new Date(year.getTime() + daysToFriday * DAY);
	    		  var nthFriday = new Date(fridayOfFirstWeek.getTime() + (7 * (dateData[1] - 1) * DAY));
	    		  var deneme = nthFriday;
	    		
//		    	var date       = new Date(dateData[0], 0, 1);
//		        weekTime   = weeksToMilliseconds(dateData[1]);
//		        targetTime = weekTime + date.getTime();
//		    	var month = date.setTime(targetTime).getMonthName();
	          
	    		
	    		}
	    	
//          var tableHeaders = "  <th style='width: 5%'>Task No</th><th>Task Adi</th><th>Task Assigment</th><th style='width: 5%'>Aciliyet</th> "
//          							+"<th style='width: 5%'>İş Tanımı</th><th style='width: 7%'>Status</th>"
//                                   +" <th>Kategori</th> "
//                                    +"<th >Açılış Tar.</th>"
//                                    +"<th>Başlama Tar.</th>"
//                                    +"<th>Kapanış Tar.</th>"
//                                  +"<th>Talep Sahibi</th>"
//                                  +"<th>Talep Yöneticisi</th>";        
          
          
          var column_names = ['Task No','Task Adi','Task Assigment','Priority','Aciliyet','İş Tanımı','Status','Kategori','Açılış Tar.','Başlama Tar.','Kapanış Tar.','Talep Sahibi','Talep Yöneticisi','Acılış Ay-Hafta','Kapanış Ay-Hafta'];
	        var columns = [];
	        for (var i = 0; i < column_names.length; i++) {
	            columns[i] = {
	                'title': column_names[i]   
	            }
	        };
          
          
          var dataSet=[];
          for (var int = 0; int < data.length; int++) {
        	  var monthNames = [ "OCAK", "ŞUBAT", "MART", "NİSAN","MAYIS","HAZIRAN", "TEMMUZ", "AGUSTOS", 
        	                       "EYLÜL", "EKİM", "KASIM", "ARALIK" ];
        	  	var bas = "";
        	  	var bit = "";
        	  	
        	  	if(data[int].openWeek != '')
        	  		{
        	  			var array = data[int].openWeek.split("-");
        	  			var date = new Date(array[0], (array[1] - 1), array[2]);
        	  			
        	  			var weekNum = week(date);
        	  			
        	  			bas = array[0] +"/" +monthNames[date.getMonth()]+"-W"+weekNum;
        	  		}

        	  	if(data[int].closeWeek != '' && data[int].closeWeek != null)
        	  		{
        	  			var array2 = data[int].closeWeek.split("-");
        	  			var date2 = new Date(array2[0], (array2[1] - 1), array2[2])
        	  			var weekNum = week(date2);
        	  			
        	  			bit = array2[0] +"/" +monthNames[date2.getMonth()]+"-W"+weekNum;
        	  		}
        	  
                 var data2 = [data[int].taskNo,data[int].taskName,data[int].taskSahibi,data[int].priority,data[int].acil,data[int].isTanimi,data[int].status,data[int].kategori,data[int].openWeek,data[int].assigmnetDate,data[int].closeWeek,data[int].talepSahibi,data[int].yonetici,bas,bit];
                 dataSet.push(data2);
          }
          
//          $("#datatablesdiv").append('<table id="displayTable" class="table responsive table-striped table-bordered table-hover" cellspacing="0"  width="100%">');
//          $("#tableDiv").find("table thead tr").append(tableHeaders);
//          $("#tableDiv").append('<table id="displayTable" class="table table-striped table-bordered"><thead><tr>' + tableHeaders + '</tr></thead></table>');      
          $('#displayTable').DataTable({        
              data: dataSet,
              columns: columns,
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
      		  },
              columnDefs: [
                           {
                               targets:0,
                               render: function ( data, type, row, meta ) {
                                   if(type === 'display'){
                                	   data = '<a href="taskupdate?taskid=' + encodeURIComponent(data) + '">' + data + '</a>';
                                   }
                                   return data;
                               }
                           },
                           {
                        	   targets:13,
                        	   className: 'never'
                           },
                           {
                        	   targets:14,
                        	   className: 'never'
                           }
                       ] 
              
          }); 
          $body.removeClass("loading"); 
      
    }
});
		
	}

	function week(d) { 
  
		  var target  = new Date(d.valueOf());  

		  var dayNr   = (d.getDay() + 6) % 7;  

		  target.setDate(target.getDate() - dayNr + 3);  

		  var jan4    = new Date(target.getFullYear(), 0, 4);  

		  var dayDiff = (target - jan4) / 86400000;    
    
		  var weekNr = 1 + Math.ceil(dayDiff / 7);    

		  return (weekNr-1);    

		}

	
});


