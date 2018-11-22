package org.ono.services.impl;

/**
 * Created by ono on 2018/11/20.
 */
public class Storage {

    private String type;
    private String address;
    private String user;
    private String password;


    public Storage() {
    }

    public Storage(String type, String address) {
        this.type = type;
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (type != null ? !type.equals(storage.type) : storage.type != null) return false;
        return address != null ? address.equals(storage.address) : storage.address == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Storage{" +
                "type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
