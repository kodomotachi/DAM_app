package com.example.demo_testing.roles;
import com.example.demo_testing.interfaces.RoleBehavior;

public class AdminRoleBehavior implements RoleBehavior {
    public boolean canRead() {
        return true;
    }
    public boolean canAdd() {
        return true;
    }
    public boolean canModify() {
        return true;
    }
    public boolean canDelete() {
        return true;
    }
    public boolean canGrantPermission() {
        return true;
    }
    public boolean canRevokePermission() {
        return true;
    }

    public boolean canMoveStore() {
        return true;
    }
    
}