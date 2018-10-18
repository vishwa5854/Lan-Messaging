package com.vishwa.lanmessaging;

import java.util.HashMap;

class UserData {

    private HashMap<String, User> _users;

    UserData() {
        initialise();
    }

    private void initialise() {
        _users = new HashMap<>();
        _users.put("ravi", new User("ravi", "ravi123"));
        _users.put("vishwa", new User("vishwa", "vishwa123"));
        _users.put("shashi", new User("shashi", "shashi123"));
        _users.put("riteesh", new User("riteesh", "riteesh123"));
        _users.put("teja", new User("teja", "teja123"));
        _users.put("vamsi", new User("vamsi", "vamsi123"));
        _users.put("chaitanya", new User("chaitanya", "chaitanya123"));
        _users.put("madhu", new User("madhu", "madhu123"));
        _users.put("phani", new User("phani", "phani123"));
        _users.put("sriram", new User("sriram", "sriram123"));
        _users.put("dheeraj", new User("dheeraj", "dheeraj123"));
        _users.put("riyaz", new User("riyaz", "riyaz123"));
        _users.put("sai", new User("sai", "sai123"));
        _users.put("gopi", new User("gopi", "gopi123"));
        _users.put("rakesh", new User("rakesh", "rakesh123"));
    }

    HashMap<String, User> getUsers() {
        return _users;
    }
}

class User {
    private String _userName;
    private String _password;

    User(String userName, String pwd) {
        _userName = userName;
        _password = pwd;
    }

    String getPassword() {
        return _password;
    }

    String getUserName() {
        return _userName;
    }
}
