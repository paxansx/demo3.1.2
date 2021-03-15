function show()
{
    $.ajax({

        cache: false,
        success:  fetch('/api/users').then(
            res=>{
                res.json().then(
                    data=>{
                        console.log(data);
                        var temp ="";

                        data.forEach((u)=>{
                            function createTable(u){

                            }
                            var rol="";
                            u.roles.forEach((r)=>{
                                if (r.role=="ROLE_ADMIN"){
                                    rol+="ADMIN "
                                }else {
                                    rol+="USER "
                                }
                            })
                            temp+="<tr>";
                            temp+="<td>"+u.id+"</td>";
                            temp+="<td>"+u.firstName+"</td>";
                            temp+="<td>"+u.lastName+"</td>";
                            temp+="<td>"+u.age+"</td>";
                            temp+="<td>"+u.name+"</td>";

                            temp+="<td>"+rol+"</td>";
                            temp+="<td><a href=\"/api/users/"+u.id+"\" class=\"btn btn-primary eBtn\">Edit</a></td>";
                            temp+="<td><a href=\"/api/users/"+u.id+"\" class=\"btn btn-danger dBtn\">Delete</a></td>";
                            temp+="</tr>";
                        })


                        document.getElementById("data1").innerHTML=temp;

                        $('.table .eBtn').on('click', function (event) {
                            event.preventDefault();
                            var href = $(this).attr('href');

                            $.get(href, function (user, status) {
                                $('.editForm #id').val(user.id);
                                $('.editForm #firstName1').val(user.firstName);
                                $('.editForm #lastName1').val(user.lastName);
                                $('.editForm #age1').val(user.age);
                                $('.editForm #email1').val(user.name);


                            });
                            $('.editForm #editModal').modal();
                        });
                        $('.table .dBtn').on('click', function (event) {
                            event.preventDefault();
                            var href = $(this).attr('href');

                            $.get(href, function (user, status) {
                                $('.deleteForm #id2').val(user.id);
                                $('.deleteForm #firstName2').val(user.firstName);
                                $('.deleteForm #lastName2').val(user.lastName);
                                $('.deleteForm #age2').val(user.age);
                                $('.deleteForm #email2').val(user.name);

                            });
                            $('.deleteForm #deleteModal').modal();
                        });

                    }
                )
            }
        )


    });
}


function activaTab(tab){
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
};

$(document).ready(function () {

    show();
    setInterval('show()',3000);

    $('.addBtn').click( function(){
        activaTab('allUsers');
        fetch('/api/users', {
            method: 'post',
            body: JSON.stringify({firstName: $('#firstName').val(), lastName: $('#lastName').val(),
                age: $('#age').val(), name: $('#email').val(), password: $('#password').val(),roles : $('#roles').val()}),
            headers: {
                'content-type': 'application/json'
            }
        })

            .then(status)
            .then(json)
            .then(data => {
                console.log('data', data);
            })
            .catch(error => {
                console.log('error', error);
            })


});
    $('.upBtn').click( function(){

        fetch('/api/users', {
            method: 'put',
            body: JSON.stringify({id: $('#id').val(), firstName: $('#firstName1').val(), lastName: $('#lastName1').val(),
                age: $('#age1').val(), name: $('#email1').val(), password: $('#password1').val(),roles : $('#roles1').val()}),
            headers: {
                'content-type': 'application/json'
            }
        })

            .then(status)
            .then(json)
            .then(data => {
                console.log('data', data);
            })
            .catch(error => {
                console.log('error', error);
            })


    });
    $('.remBtn').click( function(){
        let id = $('#id2').val()
        fetch('/api/users/'+id, {
            method: 'DELETE',

        })
            .then(res => res.json())
            .then(res => console.log(res))

    });

});





