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
        $logoutBtn = $('#loginBtn');
        $logoutBtn.click(logout);
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
    // function updateProfile() { … }
    // function logout() { … }
})();
