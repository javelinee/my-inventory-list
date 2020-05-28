package com.project.myinventory;

public class Inventory {
    private String Id;
    private String Name;
    private String Desc;
    private String Qty;

    public Inventory(){}

    public Inventory(String id, String name, String desc, String qty) {
        Id = id;
        Name = name;
        Desc = desc;
        Qty = qty;
    }

    public Inventory(String name, String desc, String qty) {
        Name = name;
        Desc = desc;
        Qty = qty;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }
}