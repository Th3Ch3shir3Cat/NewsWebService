$(document).ready(function() {
    const editor = grapesjs.init({
        container: "#editor",
        fromElement: true,
        width: "auto",
        height: "500px",
        storageManager: false,
        panels: {default: []},
        blockManager: {
            blocks: [{
                    id: "newsTitle",
                    label: "Title",
                    content: '<h3 th:text = "${news.title}">\n' +
                        'Title</h3>'
                },
                {
                    id: "bodyNews",
                    label: "Body news",
                    content: '<p th:text = "${news.bodyNews}">\n' +
                        'Body news</p>'
                },
                {
                    id: "publicationDate",
                    label: "Date of publication",
                    content: '<span th:text = "${news.publicationDate}">Date of publication</span>'
                }
            ]
        }
    })

    function returnHtml(){
        return editor.getHtml();
    }

    $("#saveTemplate").click(function(){
        $("#templateNews").val(returnHtml());
        var forms = document.getElementById("formForBody")
        var data = new FormData(forms);
        $.ajax({
            type: "POST",
            url: "/saveTemplate",
            data: data,
            processData: false,
            contentType: false,
            success: function(){
            }
        })
        window.location = "/";
    })
})