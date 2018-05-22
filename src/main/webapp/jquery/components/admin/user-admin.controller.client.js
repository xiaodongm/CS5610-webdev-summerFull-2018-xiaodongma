/**
 * This file implement Controllers that are responsible for handling user generated input and events,
 * and providing data for rendering the view. The user admin controller will handle events such as
 * create user, find all users, find user by id, update user, and delete user.
 */
(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    /**
     * Executes on document load, when the browser is done parsing the html page and the dom is ready.
     * Retrieved the dom elements needed later in the controller such as the form elements, action icons,
     * and templates. Binds action icons, such as create, update, select, and delete, to respective event handlers
     */
    function main() {
        $tbody = $('tbody');
        $userRowTemplate = $('.wbdv-template');
        $('.wbdv-create').click(createUser);
        findAllUsers();
    }

    /**
     * Handles create user event when user clicks on plus icon. Reads from the form elements and creates a user object.
     * Uses the user service createUser() function to create the new user. Updates the list of users on server response
     */
    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var email = $('#emailFld').val();
        var phone = $('#phoneFld').val();
        var dob = $('#dobFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phone: phone,
            dob: dob
        };

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
        userService
            .findUserById()
            .then(renderUsers)
    }

    /**
     * Handles delete user event when user clicks the cross icon. Reads the user is from the icon id attribute.
     * Uses user service deleteUser() to send a delete request to the server. Updates user list on server response
     */
    function deleteUser() {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');
        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    /**
     * Handles update user event when user clicks on check mark icon. Reads the user is from the icon id attribute.
     * Reads new user values form the form, creates a user object and then uses user service updateUser() to send
     * the new user data to server. Updates user list on server response
     */
    function updateUser() {
        userService
            .updateUser()
            .then(renderUsers)
    }

    /**
     * Accepts a user object as parameter and updates the form with the user properties
     * @param users
     */
    // function renderUser(user) { … }

    /**
     *
     * Accepts an array of user instances, clears the current content of the table body, iterates over the array of
     * users, clones a table row template for each user instance, populates the table row with the user object properties,
     * adds the table row to the table body
     * @param users
     */
    function renderUsers(users) {
        tbody.empty();
        for(var i = 0; i < users.length; i++){
            var user = users[i];
            var clone = userRowTemplate.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);
            clone.find('.username')
                .html(user.username);
            tbody.append(clone);
        }
    }
})();
