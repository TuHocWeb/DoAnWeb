jQuery('#validate_formproduct').validate({

    rules:
    {
        name:"required",
        email:{
            required:true,
            email:true
        },
        sdt:"required",
        number:"required",
        ghichu:"required",
        diachi:"required"
    },
    messages:
    {
        name:"Vui lòng nhập Tên",
        sdt:"Vui lòng nhập số điện thoại",
        number:"Vui lòng nhập số lượng",
        ghichu:"Vui lòng nhập ghi chú",
        diachi:"Vui lòng nhập địa chỉ"
    }
});
function check()
{
     var name=document.getElementById('name').value.trim();
     var email=document.getElementById('email').value.trim();
     var sdt=document.getElementById('sdt').value.trim();
     var soluong=document.getElementById('soluong').value.trim();
     var ghichu=document.getElementById('ghichu').value.trim();
     var diachi=document.getElementById('diachi').value.trim();
     var checkbox=document.getElementById('check');
     var isvale=true;
     if(name===''|| email==='' ||sdt==='' || soluong===''||ghichu===''||diachi==='')
     {
      isvale=false;
     }
     if(checkbox.checked==false)
     {
      isvale=false;
     }
     if(isvale)
     {
      alert("Đặt Hàng Thành Công")
     }
     
}