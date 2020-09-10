
//Model

package com.sharepunjabishayari;

public class Model {
    String id;
    String addshayari,likebtn;

    public Model(String username) {
        this.username = username;
    }

    String username;

    public Model(String id, String addshayari, String likebtn) {
        this.id = id;
        this.addshayari = addshayari;
        this.likebtn = likebtn;
    }

    public Model() {
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", addshayari='" + addshayari + '\'' +
                ", likebtn='" + likebtn + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getLikebtn() {
        return likebtn;
    }

    public void setLikebtn(String likebtn) {
        this.likebtn = likebtn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddshayari() {
        return addshayari;
    }

    public void setAddshayari(String addshayari) {
        this.addshayari = addshayari;
    }
}







   
