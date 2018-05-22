/**
 * This file contains the user admin service implements all data access to the server.
 */
function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'http://localhost:8080/api/user';
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
}
