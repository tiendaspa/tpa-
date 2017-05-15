$(document).ready(function(){

$('.ui.dropdown').dropdown();
   
$('#changePicture').click(function(){
    $('.ui.modal').modal('show');
}); 
$('#probando').click(function(){
    $('.ui.labeled.icon.sidebar')
  .sidebar('toggle')
;
});
});

