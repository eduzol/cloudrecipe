var Controller ={};

( function(Controller){
	
	
	Controller.log =function (message){
						if ( console)
								console.log(message);
					};
					
					
	Controller.fillList = 
				function( ){
			
						var index = Model.index;
						
						$('#txt-recipe-name').val(Model.recipes[index].name);
						$('#txt-recipe-description').val(Model.recipes[index].description);
						
				};
				
				
	Controller.fillSteps = function(){
		
						
						var url = Model.host+'/user/'+Model.username+'/recipes/'+Model.recipes[Model.index].recipeId+'/steps';
						
						//get recipes 
						Controller.log( 'steps url ' + url);
						
						$.ajax(url ,{
								success: function(data, textStatus, jqXHR){
									
									//alert(data.length);
									
									var steps =[];
									
									for (var i =0; i < data.length ; i++){
											steps[data[i].stepNumber] = data[i];
											
									}
									/*
									for( index in steps){
										
										alert(steps[index].stepNumber);
									}
										*/								
									for( index in data){
												$(".recipe-steps-list").append('<li>'+data[index].name+'</li>');
									}
			 					
								}
						} 
						);
	
				};		
				
	
})(Controller);

$(document).ready(
		function(){
			
			var host = 'http://localhost:8080/cloudrecipe';
			Model.host =host;
			$('div.recipe-content').hide();
			
			$('div.login-input button').click(
				function(){
					
					var username = $('#txt-recipe-username').val();
					Model.username = username;
					var url = host+'/user/'+username+'/recipes';
					
					
					Controller.log( 'url ' + url);
					
					$.ajax(url ,{
						 		success: function(data, textStatus, jqXHR){
						 				
						 					Model.recipes = data;
						 					Controller.fillList();
						 					$('div.recipe-content').show();
						 					$('div.login').hide();
						 					Controller.fillSteps();
						
						 				},
						 		error: function(){
						 					alert('failure');
						 				}
								} );

				}	
			);
			
			
		}
);

