var Controller ={};

( function(Controller){
	
	
	Controller.log =function (message){
						if ( console)
								console.log(message);
					};
					
	
	Controller.fillRecipe = function(){
		
			$('#txt-recipe-name').val(Model.recipes[Model.index].name);
			$('#txt-recipe-description').val(Model.recipes[Model.index].description);
		
	};		
					
	Controller.fillList = 
				function( ){
			
						
						Controller.fillRecipe();
						
						for( index in Model.recipes){
		
								$('.recipe-list').append('<li id="recipe-'+index+'" class="recipe-list-item"><a href="#">'+Model.recipes[index].name+'</a></li>');
								
								
						}
						
						
						$('.recipe-list-item').click(
								function(){
										var index =$(this).attr('id').split('-')[1];
										Model.index = index;
										Controller.fillRecipe();
										Controller.fillSteps();
										$('.recipe-list-item').removeClass('active');
										$(this).addClass('active');
										return false;
									
								}
						);
						
				};
				
				
	Controller.fillSteps = function(){
		
						
						var url = Model.host+'/user/'+Model.username+'/recipes/'+Model.recipes[Model.index].recipeId+'/steps';
						
						//get recipes 
						Controller.log( 'steps url ' + url);
						//remove existing recipe steps, if any
						$('div[id^="row-"]').remove();
						
						$.ajax(url ,{
								success: function(data, textStatus, jqXHR){
									
									var steps =[];
									
									for (var i =0; i < data.length ; i++){
											steps[(data[i].stepNumber-1)] = data[i];
									}
									
									var rowId = 0;
									for( index in steps){
										
										
										if ( index % 3 == 0  ){
											if( index !=0){
												rowId++;
											}
											//add row
											$(".recipe-container").append($('<div class="row-fluid"> </div>').attr('id', 'row-'+rowId));
																					
										}
										
										$step = $('<div class="span4 recipe-step"> </div>');
										$step
											.append('<h2>'+steps[index].stepNumber+ '. '+steps[index].name+'</h2>')
											.append('<p>' +steps[index].description +'</p>');
										
									
									
										$('#row-'+rowId).append($step);
										
										
									}
			 					
								}
						} 
						);
	
				};		
				
	
})(Controller);

$(document).ready(
		function(){
			
			
			//$('div.recipe-content').hide();
			
			$('#login-button').click(
				function(){
					
					var username = $('#txt-recipe-username').val();
					Model.username = username;
					var url = Model.host+'/user/'+username+'/recipes';
					
					
					Controller.log( 'url ' + url);
					
					$.ajax(url ,{
						 		success: function(data, textStatus, jqXHR){
						 				
						 					Model.recipes = data;
						 					Controller.fillList();
						 					//$('div.recipe-content').show();
						 					$('#li-login').hide();
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

