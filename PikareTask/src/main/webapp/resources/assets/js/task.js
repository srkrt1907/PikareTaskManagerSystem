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
		sweetAlert("Hata", "Kapanma haftasi açılış haftasından önceki bir tarih olamaz", "error");
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



