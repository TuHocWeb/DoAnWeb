
var swiper = new Swiper(".review_slider", {
    spaceBetween: 20,
    centeredSlides: true,
    autoplay: {
      delay: 2500,
      disableOnInteraction: false,
    },
    loop:true
  });

  let menu=document.querySelector('#menu-bar');
  let navbar=document.querySelector('#nav');
  
  menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
  }
