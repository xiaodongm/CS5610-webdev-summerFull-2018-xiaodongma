/**
 * This file implements the controller handling user events and rendering dynamic portions of the user admin page
 */

(function () {
    var $usernameFld, $firstnameFld, $lastnameFld;
    var $phoneFld, $emailFld, $roleFld;
    var $dateOfBirthFld;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();
    var currentUser = null;
    $(main);

    function main() {
        $usernameFld = $('#username');
        $firstnameFld = $('#firstname');
        $lastnameFld = $('#lastname');
        $phoneFld = $('#phone');
        $emailFld = $('#email');
        $roleFld = $('#role');
        $dateOfBirthFld = $('#dateOfBirth');
        $updateBtn = $('#updateBtn');
        $updateBtn.click(updateProfile);
        $logoutBtn = $('#logoutBtn');
        $logoutBtn.click(logout);
        getUserInfo();
    }
    
    function renderUserInfo(user) {
        currentUser = user;
        $usernameFld.val(user.username);
        $firstnameFld.val(user.firstName);
        $lastnameFld.val(user.lastName);
        $phoneFld.val(user.phone);
        $emailFld.val(user.email);
        $roleFld.val(user.role);
        $dateOfBirthFld.val(user.dateOfBirth);
    }
    
    function getUserInfo() {
        userService
            .getUserInfo()
            .then(function (response) {
                renderUserInfo(response);
            })
    }

    function updateProfile() {
        if(currentUser === null) {
            $('#updateFailAlert').css('display', 'block');
            $('#updateFailAlert').delay(2000).slideUp(200);
            return;
        }
        var user = new User($usernameFld.val(), null, $firstnameFld.val(), $lastnameFld.val(), $roleFld.val(), $emailFld.val(),
            $phoneFld.val(), $dateOfBirthFld.val());
        userService.updateProfile(user).then(function (response) {
            renderUserInfo(response);
        });
        $('#updateSuccessAlert').css('display', 'block');
        $('#updateSuccessAlert').delay(2000).slideUp(200);
    }

    function logout() {
        currentUser = null;
        userService
            .logout()
        $('#logoutAlert').css('display', 'block');
        $('#logoutAlert').delay(2000).slideUp(200);
    }

})();
