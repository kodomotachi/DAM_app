package models;
import roles.Role;
public class Permission {
    private User user;
    private Role role;
    private Store store;

    public Permission(User user, Role role, Store store) {
        this.user = user;
        this.role = role;
        this.store = store;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }
}
