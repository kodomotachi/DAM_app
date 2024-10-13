package models;
import java.util.List;
import java.util.ArrayList;;

public class Store {
    protected String name;
    protected String ownerEmail;
    List<Permission>  permissions;
    protected boolean isDeleted = false;
    
    public Store(String name, String ownerEmail) {
        this.name = name;
        this.ownerEmail = ownerEmail;
        this.permissions = new ArrayList<Permission>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

}
