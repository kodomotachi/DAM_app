package models;

import java.util.List;
import java.util.ArrayList;

public abstract class Container extends Store {
    protected List<Store> childStores;

    public Container(String name) {
        super(name);
        this.childStores = new ArrayList<Store>();
    }

}
