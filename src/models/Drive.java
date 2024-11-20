package models;
import java.util.ArrayList;


public class Drive extends Container{
    public Drive(String name, User owner) {
        super(name, owner);
        childStores = new ArrayList<Store>();
    }
    
    
}
