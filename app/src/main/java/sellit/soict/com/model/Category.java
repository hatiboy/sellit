package sellit.soict.com.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class Category {

    private String id;
    private String name;
    private CategoryGroup categoryGroup;

    public Category() {
    }

    public Category(String id, String name, CategoryGroup categoryGroup) {
        this.id = id;
        this.name = name;
        this.categoryGroup = categoryGroup;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(CategoryGroup categoryGroup) {
        this.categoryGroup = categoryGroup;
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


    public static class CategoryGroup {
        private long idGroup;
        private String nameGroup;

        public CategoryGroup() {
        }

        public CategoryGroup(long idGroup, String nameGroup) {
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

//    public static ArrayList<String> getGroupCategory(ArrayList<Category> listCategory) {
//        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
//        ArrayList<String> listgroup = new ArrayList<>();
//
//        for (int i = 0; i < listCategory.size(); i++) {
//            listgroup.add(listCategory.get(i).getNameGroup());
//        }
//        Set<String> uniqueGas = new HashSet<String>(listgroup);
//        ArrayList<String> strings = new ArrayList<>(uniqueGas);
//        strings.add(0, "Tất cả");
//        return strings;
//    }
//
//    public static ArrayList<Category> getListCategoryFromGroup(String group, ArrayList<Category> listCategory) {
//        ArrayList<Category> categories = new ArrayList<>();
//        for (int i = 0; i < listCategory.size(); i++) {
//            if (listCategory.get(i).getNameGroup().equalsIgnoreCase(group)) {
//                categories.add(listCategory.get(i));
//            }
//        }
//        Category category = new Category(0, "Tất cả", categories.get(0).idGroup, categories.get(0).nameGroup);
//        categories.add(0, category);
//        return categories;
//    }
}
