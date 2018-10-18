package com.vishwa.lanmessaging;

class UserData {
    User users[] = new User[15];

    UserData() {
        for (int i = 0; i < 15; i++) {
            users[i] = new User();
        }
        users[0] = new User("ravi", "ravi123");
        users[1] = new User("vishwa", "vishwa123");
        users[2] = new User("shashi", "shashi123");
        users[3] = new User("riteesh", "riteesh123");
        users[4] = new User("teja", "teja123");
        users[5] = new User("vamsi", "vamsi123");
        users[6] = new User("chaitanya", "chaitanya123");
        users[7] = new User("madhu", "madhu123");
        users[8] = new User("phani", "phani123");
        users[9] = new User("sriram", "sriram123");
        users[10] = new User("dheeraj", "dheeraj123");
        users[11] = new User("riyaz", "riyaz123");
        users[12] = new User("sai", "sai123");
        users[13] = new User("gopi", "gopi123");
        users[14] = new User("rakesh", "rakesh123");
    }

    int searchAndGetUserIndex(String uname) {
        int i;
        for (i = 0; i < 15; i++) {
            if (uname.compareTo(users[i].Username) == 0) {
                break;
            }
        }
        return i;
    }
}

class User {
    String Username;
    String Password;

    User() {
    }

    User(String userName, String pwd) {
        Username = userName;
        Password = pwd;
    }
}
