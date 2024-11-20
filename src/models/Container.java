package models;
import java.util.ArrayList;
import roles.Role;


public class Container extends Store {
    protected ArrayList<Store> childStores;
    public Container(String name, User owner) {
        super(name, owner);
        childStores = new ArrayList<Store>();}
    public void addChildStore(Store store) {
        childStores.add(store);
        propagatePermission();
    }
    public ArrayList<Store> getChildStores() {
        return childStores;
    }
    public void setChildStores(ArrayList<Store> childStores) {
        this.childStores = childStores;
    }
    public boolean contains(Store store) {
        return childStores.contains(store);
    }
    @Override
    public void propagatePermission() {
        for (Store child : childStores) {
            ArrayList<UserPermission> parentPermissions = getPermissions();
            ArrayList<UserPermission> childPermissions = child.getPermissions();
            childPermissions.addAll(parentPermissions); // 1 user can have multiple roles
            child.propagatePermission();
        }
    }
    public void mergePermission(ArrayList<UserPermission> permissions1, ArrayList<UserPermission> permissions2){
        for(UserPermission permission1: permissions1) {
            for(UserPermission permission2: permissions2) {
                if(permission1.getUser().equals(permission2.getUser())) {// if user is the same
                    Role role = roleCompare(permission1.getRole(), permission2.getRole());// compare the role
                    permission1.setRole(role);
                }else {
                    permissions1.add(permission2);
                }
            }
        }
    }
    public Role roleCompare(Role role1, Role role2) {
        if(role1 == Role.ADMIN) {
            return role1;
        } else if(role1 == Role.CONTRIBUTOR) {
            if(role2 == Role.ADMIN) {
                return role2;
            } else {
                return role1;
            }
        } else {
            return role2;
        }
    }
    @Override
    public void delete() {
        for(Store child: childStores) {
        child.delete();
        }
        isDeleted = true;
    }
}
