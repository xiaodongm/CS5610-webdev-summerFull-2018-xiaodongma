/**
 *  This file implements the controller handling user events and rendering dynamic portions of the user admin page
 */
(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $loginBtn = $('#loginBtn');
        $loginBtn.click(login);
    }
    function login() {
        $usernameFld = $('#username').val();
        $passwordFld = $('#password').val();

        var user = new User($usernameFld, $passwordFld);
        userService.login(user).then(function (response) {
            if(response.ok){
                alert('Login Successfully');
            }else{
                alert('Login Failed');
            }
        })
    }

})();
