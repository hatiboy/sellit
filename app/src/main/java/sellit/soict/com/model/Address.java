package sellit.soict.com.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class Address {

    private String id;
    private String name;
    private AddressGroup addressGroup;


    public Address() {
//        Log.e("ADDRESS","khoi dung");
    }

    public Address(String id, String name, AddressGroup addressGroup) {
        this.id = id;
        this.name = name;
        this.addressGroup = addressGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressGroup getAddressGroup() {
        return addressGroup;
    }

    public void setAddressGroup(AddressGroup addressGroup) {
        this.addressGroup = addressGroup;
    }

    public static class AddressGroup {
        private long idGroup;
        private String nameGroup;

        public AddressGroup() {
        }

        public AddressGroup(long idGroup, String nameGroup) {
            this.idGroup = idGroup;
            this.nameGroup = nameGroup;
        }

        public long getIdGroup() {
            return idGroup;
        }

        public void setIdGroup(long idGroup) {
            this.idGroup = idGroup;
        }

        public String getNameGroup() {
            return nameGroup;
        }

        public void setNameGroup(String nameGroup) {
            this.nameGroup = nameGroup;
        }

        @Override
        public String toString() {
            return nameGroup;
        }
    }


//    public static ArrayList<String> getGroupAddress(ArrayList<Address> listAddress) {
//        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
//        ArrayList<String> listgroup = new ArrayList<>();
//
//        for (int i = 0; i < listAddress.size(); i++) {
//            listgroup.add(listAddress.get(i).getNameGroup());
//        }
//        Set<String> uniqueGas = new HashSet<String>(listgroup);
//        ArrayList<String> strings = new ArrayList<>(uniqueGas);
//        strings.add(0, "Tất cả");
//        return strings;
//    }
//
//    public static ArrayList<Address> getListAdressFromGroup(String group, ArrayList<Address> listAddress) {
//        ArrayList<Address> addresses = new ArrayList<>();
////       long  idGroup;
////        String nameGroup;
//        for (int i = 0; i < listAddress.size(); i++) {
//            if (listAddress.get(i).getNameGroup().equalsIgnoreCase(group)) {
////                idGroup=listAddress.get(i).idGroup;
////                nameGroup=listAddress.get(i).nameGroup ;
//                addresses.add(listAddress.get(i));
//            }
//        }
//        Address address = new Address(0, addresses.get(0).nameGroup, addresses.get(0).idGroup, "Tất cả");
//        addresses.add(0, address);
//        return addresses;
//    }
}
