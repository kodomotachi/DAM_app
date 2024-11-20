package roles;
import interfaces.RoleBehavior;

public class ContributorRoleBehavior implements RoleBehavior {
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
