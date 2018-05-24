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
        // $usernameFld = $('#username');
        $firstnameFld = $('#firstname');
        $lastnameFld = $('#lastname');
        $phoneFld = $('#phone');
        $emailFld = $('#email');
        $roleFld = $('#role');
        $dateOfBirthFld = $('#dateOfBirth');
        $updateBtn = $('#updateBtn');
        $updateBtn.click(updateProfile);
        // $logoutBtn = $('#loginBtn');
        // $logoutBtn.click(logout);
        getUserInfo();
    }
    
    function renderUserInfo(user) {
        // $usernameFld.val(user.username);
        $firstnameFld.val(user.firstName);
        $lastnameFld.val(user.lastName);
        $phoneFld.val(user.phone);
        $emailFld.val(user.email);
        $roleFld.val(user.role);
        var date;
        if(user.dateOfBirth != null){
            date = user.dateOfBirth.split('T')[0];
        }
        $dateOfBirthFld.val(date);
    }
    
    function getUserInfo() {
        userService
            .getUserInfo()
            .then(function (response) {
                renderUserInfo(response);
            })
    }
    function updateProfile() {
        var user = new User(null, null, $firstnameFld.val(), $lastnameFld.val(), $roleFld.val(), $emailFld.val(),
            $phoneFld.val(), $dateOfBirthFld.val());

        console.log(user);
        userService.updateProfile(user).then(function (response) {
            renderUserInfo(response);
        })
    }
    // function logout() { … }
})();
