/**
 * This file contains the register controller handling user events and rendering
 * dynamic portions of the user admin page
 */
(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $registerBtn = $('#register');
        $registerBtn.click(register);
    }
    function register() {
        $usernameFld = $('#username').val();
        $passwordFld = $('#password').val();
        $verifyPasswordFld = $('#verifyPassword').val();
        if($passwordFld !== $verifyPasswordFld){
            alert('Password not match');
        }
        var user = new User($usernameFld, $passwordFld, null, null, null, null, null, null);
        userService.register(user).then(function (response) {
            if(response.ok){
                window.location.href='http://localhost:8080/jquery/components/profile/profile.template.client.html';
            }else{
                alert('Can not register');
            }
        })
    }
})();
