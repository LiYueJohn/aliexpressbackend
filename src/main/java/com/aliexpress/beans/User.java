package com.aliexpress.beans;

public class User {
    private String userName;
    private String password;
    private String nickename;
    private String createtime;
    private String updatetime;
    private int role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
    this.userName = userName;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getNickename() {
    return nickename;
}

public void setNickename(String nickename) {
    this.nickename = nickename;
}

public String getCreatetime() {
    return createtime;
}

public void setCreatetime(String createtime) {
    this.createtime = createtime;
}

public String getUpdatetime() {
    return updatetime;
}

public void setUpdatetime(String updatetime) {
    this.updatetime = updatetime;
}

public int getRole() {
    return role;
}

public void setRole(int role) {
    this.role = role;
}
}
