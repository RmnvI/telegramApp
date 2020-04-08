$(document).ready(function () {

    GetAll();

    $("#edit").click(function (event) {
        event.preventDefault();
        Edit();
    });

    $("#add").click(function (event) {
        event.preventDefault();
        Add();
    });

});


// Добавление новой книги
function Add() {
    // получаем значения для добавляемой книги
    let cityInfo = {
        cityName: $('#cityName').val(),
        cityDescription: $('#cityDescription').val()
    };
    console.log(cityInfo);

    $.ajax({
        url: '/new',
        type: 'POST',
        data: JSON.stringify(cityInfo),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            location.reload();
        },
        error: function (x) {
            alert("kulgin");
        }
    });
}
// Удаление книги
function Delete(el) {
    var id = $(el).attr('data-item');
    $.ajax({
        url: '/delete/' + id,
        type: 'DELETE',
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            GetAll();
        },
        error: function (x, y, z) {
            alert(x + '\n' + y + '\n' + z);
        }
    });
}
// редактирование книги
function Edit() {
    // получаем новые значения для редактируемой книги
    var info = {
        id: $('#editId').val(),
        cityName: $('#editName').val(),
        cityDescription: $('#editDescription').val()
    };
    $.ajax({
        url: '/update',
        type: 'PUT',
        data: JSON.stringify(info),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            location.reload();
        },
        error: function (x, y, z) {
            alert(x + '\n' + y + '\n' + z);
        }
    });
}
// вывод полученных данных на экран


// обработчик редактирования
function EditItem (el) {
    // получаем id редактируемого объекта
    var id = $(el).attr('data-item');
    Get(id);
}
// вывод данных редактируемой книги в поля для редактирования
function ShowInfo(data) {
    if (data != null) {
        $("#createBlock").css('display', 'normal');
        $("#editBlock").css('display', 'normal');
        $("#editId").val(data.id);
        $("#editName").val(data.cityName);
        $("#editDescription").val(data.cityDescription);
    }
    else {
        alert("Такая книга не существует");
    }
}
// запрос книги на редактирование
function Get(id) {
    $.ajax({
        url: '/getById/' + id,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            ShowInfo(data);
        },
        error: function (x, y, z) {
            alert(x + '\n' + y + '\n' + z);
        }
    });
}



function GetAll(){
    $.ajax({
        type: "GET",
        url: "/all",
        success: function (result) {
            var strResult = "<table><th>ID</th><th>City Name</th><th>Description</th>";
            $.each(result, function (index, data) {
                strResult += "<tr><td>" + data.id + "</td><td> " + data.cityName + "</td><td>" +
                    data.cityDescription + "</td><td><button data-item='" + data.id + "' onclick='EditItem(this);' >Edit</button></td>" +
                    "<td><button id='delItem' data-item='" + data.id + "' onclick='Delete(this);' >Delete</button></td></tr>";
            });
            strResult += "</table>";
            $("#tableBlock").html(strResult);
        },
        error: function () {
            let strResult = "<h1> ERROR </h1>";
            $("#tableBlock").html(strResult);
        }

    });
}