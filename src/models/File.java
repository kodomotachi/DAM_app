package models;
import roles.Role;
public class File extends Store {
    private String content;
    public File(String name) {
        super(name);
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public void propagatePermission(User user, Role role) {
        // Do nothing
    }
    @Override
    public void delete() {
        markAsDeleted();
    }
}
