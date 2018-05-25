/**
 * This file implements the controller handling user events and rendering dynamic portions of the user admin page
 */

(function () {
    var $usernameFld, $firstnameFld, $lastnameFld;
    var $phoneFld, $emailFld, $roleFld;
    var $dateOfBirthFld;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();
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
        var user = new User($usernameFld.val(), null, $firstnameFld.val(), $lastnameFld.val(), $roleFld.val(), $emailFld.val(),
            $phoneFld.val(), $dateOfBirthFld.val());
        userService.updateProfile(user).then(function (response) {
            renderUserInfo(response);
        }).then($('#updateAlert').css('display', 'block'));
    }

    function logout() {
        userService
            .logout()
            .then($('#logoutAlert').css('display', 'block'));

    }

})();
