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
            $('#passwordAlert').css('display', 'block');
            $('#passwordAlert').delay(2000).slideUp(200);
        }else{
            var user = new User($usernameFld, $passwordFld, null, null, null, null, null, null);
            userService.register(user).then(function (response) {
                if(response.ok){
                    window.location.href='../profile/profile.template.client.html';
                }else{
                    $('#signupAlert').css('display', 'block');
                    $('#signupAlert').delay(2000).slideUp(200);
                }
            })
        }
    }
})();
