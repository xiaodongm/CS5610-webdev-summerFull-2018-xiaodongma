/**
 * This file contains the user admin service implements all data access to the server.
 */
function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.getUserInfo = getUserInfo;
    this.updateProfile = updateProfile;
    this.logout = logout;
    this.url = '/api/user';
    this.registerUrl = '/api/register';
    this.loginUrl = '/api/login';
    this.profileUrl = '/api/profile';
    this.logoutUrl = '/api/logout';
    var self = this;

    /**
     * Accepts a user object and POSTs it to a user Web service. Receives status
     * @param user
     * @param callback
     */
    function createUser(user, callback) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }


    /**
     * Sends a GET request to user Web service. Receives a JSON array of all users
     * @param callback
     */
    function findAllUsers(callback) {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    /**
     * Sends a GET request to user Web service with userId as path parameter.
     * Receives a single JSON object for the userId
     * @param userId
     * @param callback
     */
    function findUserById(userId, callback) {
        return fetch(self.url + '/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    /**
     * Accepts a user id and user object with new property values for the user with user id.
     * Sends PUT request with user object and user id as path parameter
     * @param userId
     * @param user
     * @param callback
     */
    function updateUser(userId, user, callback) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function (response) {
            return response.json();
        });
    }

    /**
     * Sends a DELETE request to user Web service with user id as path parameter for user to remove. Receives status
     * @param userId
     * @param callback
     */
    function deleteUser(userId, callback) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    /**
     * register a user if user not already registered
     */
    function register(user) {
        return fetch(self.registerUrl, {
            credentials: 'same-origin',
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response;
        })
    }

    /**
     * login a registered user
     */
    function login(user) {
        return fetch(self.loginUrl, {
            credentials: 'same-origin',
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response;
        })
    }

    /**
     * get a user's info
     */
    function getUserInfo() {
        return fetch(self.profileUrl,{
            credentials: 'same-origin',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json().then(function (result) {
                return result;
            })
        });
    }

    /**
     * update a user's profile
     */
    function updateProfile(user) {
        console.log('abcdfasfs');
        return fetch(self.profileUrl, {
            credentials: 'same-origin',
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function (response) {
                return response.json();
            });
    }

    /**
     * logout a user
     */
    function logout() {
        return fetch(self.logoutUrl, {
            credentials: 'same-origin',
            method: 'post',
            headers: {
                'content-type': 'application/json'
            }
        })
    }

}
