alert('first alert');
 $(document).ready(function(){
	alert('Jquery is called');
	$("#submit").click(function(){
		alert('onClick called');


		$.ajax({
			type: 'GET',
			url:'/bin/GetNodePropertiesServ',
			success: function(result){
				alert(result);
				},
			error: function(err){
				alert("Unable to retrieve data"+err);
			}
		}); 
	});
});


