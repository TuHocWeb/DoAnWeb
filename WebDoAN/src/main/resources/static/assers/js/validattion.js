jQuery('#validate_form').validate({

    rules:
    {
        name:"required",
        email:{
            required:true,
            email:true
        },
        pasword:"required",
        repasword:"required"
        
    },
    messages:
    {
        name:"Vui lòng nhập Tên",
        pasword:"Vui lòng nhập Pasword",
        repasword:"Vui lòng nhập lại Pasword"
        
    }
});