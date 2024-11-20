package models;
import java.util.ArrayList;

public class Folder extends Container{
    private String parentName;

    public Folder(String name, User owner) {
        super(name, owner);
        childStores = new ArrayList<Store>();
    }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
