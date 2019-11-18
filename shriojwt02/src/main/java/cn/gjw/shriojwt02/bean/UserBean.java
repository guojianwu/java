package cn.gjw.shriojwt02.bean;


public class UserBean {
    private String username;

    private String password;

    private String role;

    private String permission;

    public UserBean() {
    }

    public UserBean(String username, String password, String role, String permission) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
