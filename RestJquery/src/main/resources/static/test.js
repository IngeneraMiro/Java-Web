$(document).ready(function () {

let index = 1;

 $.getJSON('http://127.0.0.1:8080/authors/'+ index).then(function (response) {
   let info =
           '<p>'+ response.id + '</p>>'

     $('.authors-container').append(info);

 })


})