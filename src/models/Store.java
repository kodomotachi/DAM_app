package models;
import java.util.ArrayList;
import roles.Role;
import java.util.Date;

public abstract class Store implements Comparable<Store>{
    protected String name;
    protected User owner;
    protected Date createdDate;
    protected boolean isDeleted;
    protected ArrayList<UserPermission> permissions;
    
    public Store(String name,User owner) {
        this.name = name;
        this.owner = owner;
        this.createdDate = new Date();
        this.isDeleted = false;
        this.permissions = new ArrayList<UserPermission>();
        this.addPermission(owner, Role.ADMIN);
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
    public Date getCreatedDate() {
        return createdDate;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setPermissions(ArrayList<UserPermission> permissions) {
        this.permissions = permissions;
    }
    public ArrayList<UserPermission> getPermissions() {
        return permissions;
    }
    public Role checkPermission(User user) {
        for(UserPermission permission: permissions) {
            if(permission.getUser().equals(user)) {
                return permission.getRole();
            }
        }
        return null;
    }
    public void addPermission(User user, Role role) {
        for(UserPermission permission: permissions) {
            if(permission.getUser().equals(user)) {
                throw new IllegalArgumentException("User already has permission. " + permission.getUser().getEmail()+": "+permission.getRole());
            }
        }
        UserPermission permission = new UserPermission(user, this, role);
        permissions.add(permission);
    }
    public void removePermission(User user) {
        for(UserPermission permission: permissions) {
            if(permission.getUser().equals(user)) {
                permissions.remove(permission);
                return;
            }
        }
    }
    @Override
    public int compareTo(Store other){
        return this.name.compareTo(other.getName());
    }
    
    public abstract void propagatePermission();
    public abstract void delete();
}
