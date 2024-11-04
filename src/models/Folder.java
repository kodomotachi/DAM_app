package models;

import java.util.List;
import roles.Role;

public class Folder extends Container {
    public Folder(String name) {
        super(name);
    }
    public List<Store> getChildStores() {
        return childStores;
    }
    public void setChildStores(List<Store> childStores) {
        this.childStores = childStores;
    }
    public void addStore(Store store) {
        childStores.add(store);
    }
    public boolean contains(Store store) {
        return childStores.contains(store);
    }

    @Override
    public void propagatePermission(User user, Role role) {
        for (Store store : childStores) {
            store.grantPermission(user, role);
            store.propagatePermission(user, role);
        }
    }
    @Override
    public void delete() {
        for (Store store : childStores) {
            store.delete();
        }
        markAsDeleted();
    }

}
