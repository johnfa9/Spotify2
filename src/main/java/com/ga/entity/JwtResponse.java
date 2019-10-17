package com.ga.entity;

public class JwtResponse {
	//what we'll do is simply have a login request, which will then generate a web token for us, but only if the user's username and password match what we already have in the database. 
	//we'll first create a simple response object that will be sent back to the user if login is successful and then update our login() method in the UserService and UserController
	//We'll update the login() method so it returns the token.
	private String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getToken() {
        return this.jwt;
    }
}