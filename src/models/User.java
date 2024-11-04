package models;
import java.util.ArrayList;
import java.util.List;

import interfaces.RoleBehavior;
import roles.Role;
import roles.RoleBehaviorMap;

public class User {
    private String email;
    private String password;
    private List<Drive> drives;
    

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Drive> getDrives() {
        return drives;
    }
    public void addDrive(Drive drive) {
        if (drives == null) {
            drives = new ArrayList<Drive>();
        }
        drives.add(drive);
        drive.setOwner(this);
    }
    public void addStore(Store store, Drive drive) {
        Role thisUserRole = drive.getPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        
        if(roleBehavior.canAdd() && !drive.isDeleted()){
            drive.addStore(store);
            store.setOwner(this);
        } else {
            throw new IllegalStateException("User does not have permission to add store to drive");
        }
    }
    public void addStore(Store store, Folder folder) {
        Role thisUserRole = folder.getPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        
        if(roleBehavior.canAdd() && !folder.isDeleted()){
            folder.addStore(store);
            store.setOwner(this);
        } else {
            throw new IllegalStateException("User does not have permission to add store to folder");
        }
    }
    public void deleteStore(Store store) {
        Role thisUserRole = store.getPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        
        if(roleBehavior.canDelete() && !store.isDeleted()){
            store.delete();
        } else {
            throw new IllegalStateException("User does not have permission to delete store");
        }
    }
    public void grantPermission(User user, Store store, Role role) {
        Role thisUserRole = store.getPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        
        if(roleBehavior.canGrantPermission() && !store.isDeleted()){
            store.grantPermission(user, role);
        } else {
            throw new IllegalStateException("User does not have permission to grant permission");
        }
    }
    public void modifyName(Store store, String newName) {
        Role thisUserRole = store.getPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        
        if(roleBehavior.canModify() && !store.isDeleted()){
            store.setName(newName);
        } else {
            throw new IllegalStateException("User does not have permission to modify store name");
        }
    }
    public boolean canRead(Store store) {
        Role thisUserRole = store.getPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        
        return roleBehavior.canRead();
    }
}
