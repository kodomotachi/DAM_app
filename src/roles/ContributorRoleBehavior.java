package roles;

import interfaces.RoleBehavior;

public class ContributorRoleBehavior implements RoleBehavior {

    @Override
    public boolean canGrantPermission() {
        return false;
    }
    @Override
    public boolean canAdd() {
        return true;
    }

    @Override
    public boolean canModify() {
        return true;
    }

    @Override
    public boolean canDelete() {
        return true;
    }

    @Override
    public boolean canRead() {
        return true;
    }
}
