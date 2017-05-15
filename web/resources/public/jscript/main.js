$(document).ready(function(){

$('.message .close').on('click',function(){
    $(this).closest('.message').transition('fade');
})

$('#pass').click(function(){
    $('.ui.modal').modal('show');
});

$('.ui.form').form({
          
    fields: {
      email: {
        identifier  : 'email',
        rules: [
          {
            type   : 'empty',
            prompt : 'Por favor ingresa tu nombre de usuario'
          },
        ]
      },
      password: {
        identifier  : 'password',
        rules: [
          {
            type   : 'empty',
            prompt : 'Por favor ingresa tu contraseña'
          },
        ]
      }

    },

    });
    
    
$('.pruebaFrm').form({
    fields: {
      tienda: {
        identifier  : 'tienda',
        rules: [
          {
            type   : 'empty'
          }
        ]
      },
      prop: {
        identifier  : 'prop',
        rules: [
          {
            type   : 'empty'
          }
        ]
      },
      cedu: {
        identifier  : 'cedu',
        rules: [
          {
            type   : 'empty'
          },
          {
            type   : 'integer'
          }
        ]
      },
      tel: {
        identifier  : 'tel',
        rules: [
          {
            type   : 'empty'
          },
          {
            type   : 'integer'
          }
        ]
      },
      dir: {
        identifier  : 'dir',
        rules: [
          {
            type   : 'empty'
          }
        ]
      },
      usuar: {
        identifier  : 'usuar',
        rules: [
          {
            type   : 'empty'
          }
        ]
      },
      contra: {
        identifier  : 'contra',
        rules: [
          {
            type   : 'empty'
          }
        ]
      }
    },
          
});

});



