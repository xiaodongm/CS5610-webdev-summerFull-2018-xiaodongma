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

        var user = new User($usernameFld, $passwordFld, null, null, null, null, null, null);
        userService.login(user).then(function (response) {
            if(response.ok){
                window.location.href='http://localhost:8080/jquery/components/profile/profile.template.client.html';
            }else{
                alert('Login Failed');
            }
        })
    }

})();
