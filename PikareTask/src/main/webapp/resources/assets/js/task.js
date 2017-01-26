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
	
	if(assignweek < openWeek)
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

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('dataTables'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
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



$("#ara").click(function()
{
	var e = document.getElementById("kisi");
	var strUser = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("hafta");
	var hafta = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("kategori");
	var kategori = e.options[e.selectedIndex].value;
	var firstdateVal ="";
	var lastdateVal ="";
	if(hafta == ""){
		firstdateVal = $("#firstdate").val();
		lastdateVal = $("#lastdate").val();
	}
	
	
	$.ajax({
		url: "../data/filtrele",
	      type: 'GET',
	      data: "kisi=" + strUser + "&hafta=" + hafta + "&kategori=" + kategori + "&firstdate="+firstdateVal+"&lastdate="+lastdateVal,
	    success: function(data){
       
          
          var len = data.length;
          var tableHeaders = "<th>Kişiler</th><th>Alınan</th><th>Kapanan</th><th>Tümü</th>";        
          $("#tableDiv").empty();
          
          var dataSet=[];
          for (var int = 0; int < data.length; int++) {
           	  var closeTask = data[int].close;
                 var openTask = data[int].all;
                 var assignTask = data[int].open;
                 var name = data[int].name;
                 var data2 = [name,assignTask,closeTask,openTask];
                 dataSet.push(data2);
          }
             
              
              
                             
         
          
          $("#tableDiv").find("table thead tr").append(tableHeaders);
          $("#tableDiv").append('<table id="displayTable" class="table table-striped table-bordered" cellspacing="0" width="100%"><thead><tr>' + tableHeaders + '</tr></thead></table>');      
          $('#displayTable').DataTable({
              responsive: true,
              data: dataSet,
              dom: 'lBfrtip',
              buttons: [
                  'copyHtml5',
                  'excelHtml5',
                  'pdfHtml5'
              ]
          });
          
//          
//          //$("#tableDiv").find("table thead tr").append(tableHeaders);  
//           
//          for(var i = 0; i<len; i++)
//	      {  
//        	  tableBody += "<tr><td>" + data[i][1] + "</td>" + "<td>" + data[i][2] + "</td><td></td><td></td></tr>" ;
//	      }
//          
//          $("#tableDiv").append('<table id="displayTable" class="display" cellspacing="0" width="100%"><thead><tr>' + tableHeaders + '</tr></thead><tbody>'+tableBody+'</tbody></table>');
//          
//          
//          $('#displayTable').dataTable(data);
	      
	      
	      
	      
	      
	      
	    }
	});
		
});


