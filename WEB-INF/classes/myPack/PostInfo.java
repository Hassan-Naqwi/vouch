package myPack;

import java.io.*;

public class PostInfo implements Serializable {

    private int id;
    private String title;
    private String content;
    private String email;
    private String status;

    PostInfo() {
        title = "";
        content = "";
        email = "";
        status = "";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getEmail() {
        return this.email;
    }

    public String getStatus() {
        return this.status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
