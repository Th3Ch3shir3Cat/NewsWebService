$(document).ready(function(){
    var templateFromDataBase = document.getElementById("templateFromDateBase");
    if(templateFromDataBase != null) {
        loadByTemplate();
    }
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                else{
                    event.preventDefault();
                    var data = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "/addNews",
                        enctype: 'multipart/form-data',
                        data: data,
                        processData: false,
                        contentType: false,
                        success: function(result){
                            $("#arrayNews").html(result);
                            $("#modalAddNews").modal("hide");
                        }
                    })
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);


    $("#showModal").click(function(){
        $("#modalAddNews").modal("show");
        $("#addNewsForm").get(0).reset();
        $("#addNewsForm").classList.removeAttribute('was-validated');
    })
})

function loadByTemplate(){
    $.ajax({
        type: "POST",
        url: "/getListNews",
        success: function(result){
            console.log(result);
            $("#templateFromDateBase").html(result);
        }
    })
}