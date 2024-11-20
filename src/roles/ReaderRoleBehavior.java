package roles;
import interfaces.RoleBehavior;

public class ReaderRoleBehavior implements RoleBehavior {
    public boolean canRead() {
        return true;
    }
    public boolean canAdd() {
        return false;
    }
    public boolean canModify() {
        return false;
    }
    public boolean canDelete() {
        return false;
    }
    public boolean canGrantPermission() {
        return false;
    }
    public boolean canRevokePermission() {
        return false;
    }

    public boolean canMoveStore() {
        return false;
    }
}