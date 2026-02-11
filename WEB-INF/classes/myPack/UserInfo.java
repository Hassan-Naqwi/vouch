package myPack;

import java.io.*;

public class UserInfo implements Serializable {

    private String name;
    private String role;
    private String email;
    private String password;

    UserInfo() {
        name = "";
        role = "";
        email = "";
        password = "";
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}