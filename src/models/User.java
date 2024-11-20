package models;
import java.util.ArrayList;

import interfaces.RoleBehavior;
import roles.Role;
import roles.RoleBehaviorMap;

public class User implements Comparable<User> {
    private String email;
    private ArrayList<Drive> stores;
    

    public User(String email) {
        this.email = email;
        this.stores = new ArrayList<Drive>();
        
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Drive> getStores() {
        return stores;
    }

    @Override
    public int compareTo(User other) {
        return this.email.compareTo(other.getEmail());
    }


    public void createDrive(String name) { //Chưa xác định quyền của user
        Drive drive = new Drive(name, this);
        stores.add(drive);
    }
    public void createFolder(String name, Drive drive) {
        Folder folder = new Folder(name, this);
        drive.addChildStore(folder);
    }
    public void createFile(String name, Drive drive, String content) {
        File file = new File(name, this, content);
        drive.addChildStore(file);
    }

    public void addDrive(Drive drive) {
        stores.add(drive);
        drive.setOwner(this);
    }
    
    public <E extends Container> void addStore(Store Store, E container) { //Container can add file, folder to drive, folder
        Role thisUserRole = container.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        if(roleBehavior.canAdd() && !container.isDeleted()) {
            container.addChildStore(Store);
            Store.setOwner(this);
        } else {
            throw new IllegalStateException("User does not have the required permission to add Stores.");
        }
    }

    public void deleteStore(Store store) {
        Role thisUserRole = store.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);
        if(roleBehavior.canDelete() && !store.isDeleted()) {
            store.delete();
        } else {
            throw new IllegalStateException("User does not have the required permission to delete.");
        }
    }

    public void grantPermission(User user, Store store, Role role) {
        Role thisUserRole = store.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);

        if(roleBehavior.canGrantPermission() && !store.isDeleted()) {
            store.addPermission(user, role);
            store.propagatePermission();
        } else {
            throw new IllegalStateException("User does not have the required permission to grant access.");
        }
    }
    public void revokePermission(User user, Store store) {
        Role thisUserRole = store.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);

        if(roleBehavior.canRevokePermission() && !store.isDeleted()) {
            store.removePermission(user);
            store.propagatePermission();
        } else {
            throw new IllegalStateException("User does not have the required permission to revoke access.");
        }
    }
    public void moveStore(Store store, Drive drive) {
        Role thisUserRole = store.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(thisUserRole);

        if(roleBehavior.canMoveStore() && !store.isDeleted()) {
            // store.delete(); //Store still exist in the old drive, just be set isDeleted = true
            // drive.addChildStore(store);
        } else {
            throw new IllegalStateException("User does not have the required permission to move the asset.");
        }
    }

    public void rename(Store store, String newName) {
        Role storePermission = store.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(storePermission);

        if(roleBehavior.canModify() && !store.isDeleted()) {
            store.setName(newName);
        } else {
            throw new IllegalStateException("User does not have the required permission to modify the asset.");
        }
    }

    public String openFile(File file) {
        Role storePermission = file.checkPermission(this);
        RoleBehavior roleBehavior = RoleBehaviorMap.getRoleBehavior(storePermission);
    
        if(roleBehavior.canRead() && !file.isDeleted()) {
            return file.getContent();
        } else {
            throw new IllegalStateException("User does not have the required permission to view the contents.");
        }
    }
    public void editFile(File file) { // Usr only can edit if he can openFile, usr will keep modify from the current content of the file

    }

}
