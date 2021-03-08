$(document).ready(function () {

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

});
$(document).ready(function () {
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
});
