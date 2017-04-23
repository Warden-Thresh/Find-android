package com.warden.find.gson;

import java.util.List;

/**
 * Created by Warden on 2017/4/17.
 */

public class UserList {

    private List<User> userlist;

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public static class User{
        /**
         * userId : 9
         * photoNumber : null
         * email : null
         * password : dQWd
         * firstName : QWD
         * lastName : qqd
         * nickname : qWdwqeqweqeqw
         * img : http://10.0.2.2/image/banana.jpg
         */

        private int userId;
        private Object photoNumber;
        private Object email;
        private String password;
        private String firstName;
        private String lastName;
        private String nickname;
        private String img;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getPhotoNumber() {
            return photoNumber;
        }

        public void setPhotoNumber(Object photoNumber) {
            this.photoNumber = photoNumber;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
