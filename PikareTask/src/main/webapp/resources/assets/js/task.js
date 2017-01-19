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



