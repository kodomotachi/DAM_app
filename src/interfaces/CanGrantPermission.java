package interfaces;

public interface CanGrantPermission {
    boolean canGrantPermission();
    boolean canRevokePermission();
    boolean canMoveStore();
}
