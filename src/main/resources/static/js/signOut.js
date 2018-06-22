$("#signOut").click(function () {
    $.get("/user/logout",function (result) {
        console.log(result);
        window.location = "/";
    });
});