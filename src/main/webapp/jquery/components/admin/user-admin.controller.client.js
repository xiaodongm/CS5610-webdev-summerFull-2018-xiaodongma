/**
 * This file implement Controllers that are responsible for handling user generated input and events,
 * and providing data for rendering the view. The user admin controller will handle events such as
 * create user, find all users, find user by id, update user, and delete user.
 */
(function () {
    var $usernameFld, $passwordFld, $roleFld;
    var $removeBtn, $editBtn, $createBtn, $updateBtn, $userId;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();
    $(main);

    /**
     * Executes on document load, when the browser is done parsing the html page and the dom is ready.
     * Retrieved the dom elements needed later in the controller such as the form elements, action icons,
     * and templates. Binds action icons, such as create, update, select, and delete, to respective event handlers
     */
    function main() {
        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template');
        $createBtn = $('.wbdv-create');
        $createBtn.click(createUser);
        $updateBtn = $('.wbdv-update');
        $updateBtn.click(updateUser);
        findAllUsers();
    }

    /**
     * Handles create user event when user clicks on plus icon. Reads from the form elements and creates a user object.
     * Uses the user service createUser() function to create the new user. Updates the list of users on server response
     */
    function createUser() {
        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();

        var user = new User($usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld);

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    /**
     * Called whenever the list of users needs to be refreshed. Uses user service findAllUsers() to retrieve all
     * the users and passes response to renderUsers
     */
    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers)
    }

    /**
     * Called whenever a particular user needs to be retrieved by their id, as in when a user is selected for editing.
     * Reads the user is from the icon id attribute. Uses user service findUserById() to retrieve user and then updates
     * the form on server response
     */
    function findUserById() {
        return userService
            .findUserById($userId)
    }

    /**
     * Handles delete user event when user clicks the cross icon. Reads the user is from the icon id attribute.
     * Uses user service deleteUser() to send a delete request to the server. Updates user list on server response
     */
    function deleteUser() {
        $removeBtn = $(event.currentTarget);
        $userId = $removeBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        userService
            .deleteUser($userId)
            .then(findAllUsers);
        $userId = -1;
    }

    /**
     * Handles select user event when user clicks on the pen icon.
     * Select a user for update.
     */
    function selectUser() {
        $editBtn = $(event.currentTarget);
        $userId = $editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        findUserById($userId)
            .then(renderUser);
    }


    /**
     * Handles update user event when user clicks on check mark icon. Reads the user is from the icon id attribute.
     * Reads new user values form the form, creates a user object and then uses user service updateUser() to send
     * the new user data to server. Updates user list on server response
     */
    // function updateUser() {
    //     $usernameFld = $('#usernameFld').val();
    //     $passwordFld = $('#passwordFld').val();
    //     $firstNameFld = $('#firstNameFld').val();
    //     $lastNameFld = $('#lastNameFld').val();
    //     $roleFld = $('#roleFld').val();
    //
    //     var user = new User($usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld);
    //     userService
    //         .updateUser($userId, user)
    //         .then(findAllUsers);
    // }

    /**
     * Accepts a user object as parameter and updates the form with the user properties
     * @param users
     */
    function renderUser(user) {
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#roleFld').val(user.role);
    }

    /**
     *
     * Accepts an array of user instances, clears the current content of the table body, iterates over the array of
     * users, clones a table row template for each user instance, populates the table row with the user object properties,
     * adds the table row to the table body
     * @param users
     */
    function renderUsers(users) {
        $tbody.empty();
        for(var i = 0; i < users.length; i++){
            var user = users[i];
            var clone = $userRowTemplate.clone();

            clone.attr('id', user.id);

            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(selectUser);

            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            $tbody.append(clone);
        }
    }
})();
