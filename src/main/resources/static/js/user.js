function show1() {
    $.ajax({

        cache: false,
        success: fetch('/api/user').then(
            res => {
                res.json().then(
                    data => {
                        console.log(data);
                        var temp = "";


                        function createTable(data) {

                        }

                        var rol = "";
                        data.roles.forEach((r) => {
                            if (r.role == "ROLE_ADMIN") {
                                rol += "ADMIN "
                            } else {
                                rol += "USER "
                            }
                        })
                        temp += "<tr>";
                        temp += "<td>" + data.id + "</td>";
                        temp += "<td>" + data.firstName + "</td>";
                        temp += "<td>" + data.lastName + "</td>";
                        temp += "<td>" + data.age + "</td>";
                        temp += "<td>" + data.name + "</td>";

                        temp += "<td>" + rol + "</td>";

                        temp += "</tr>";


                        document.getElementById("userTable").innerHTML = temp;


                    }
                )
            }
        )


    });
}

$(document).ready(function () {

    show1();
    setInterval('show()', 10000);
});