package edu.byui.pantrypro;

/**
 * Created by User on 3/12/2018.
 */

public class Item {

    //call this _id android uses this exact name a lot
    private int _id;
    private String _itemname;
//can add more variables as needed

    public Item(String itemname){
        this._itemname = itemname;
    }

    public int get_id() {
        return _id;
    }

    public String get_itemname() {
        return _itemname;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    public void set_itemname(String _itemname) {
        this._itemname = _itemname;
    }
}
