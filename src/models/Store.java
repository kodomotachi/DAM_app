package models;
import java.util.List;
import java.util.ArrayList;
import roles.Role;

public abstract class Store {
    protected String name;
    protected User owner;
    protected List<Permission>  permissions;
    protected boolean isDeleted = false;
    
    public Store(String name) {
        this.name = name;
        this.permissions = new ArrayList<Permission>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void markAsDeleted() {
        isDeleted = true;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    public void grantPermission(User user, Role role) {
        permissions.removeIf(up -> up.getUser().equals(user));
        permissions.add(new Permission(user, this, role));

        propagatePermission(user, role);
    }
    public Role getPermission(User user) {
        for (Permission permission : permissions) {
            if (permission.getUser().equals(user)) {
                return permission.getRole();
            }
        }
        return null;
    }

    public abstract void propagatePermission(User user, Role role);
    public abstract void delete();
}
