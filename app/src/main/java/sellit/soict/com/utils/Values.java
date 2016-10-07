package sellit.soict.com.utils;

import java.util.ArrayList;

import sellit.soict.com.model.Address;
import sellit.soict.com.model.Category;
import sellit.soict.com.model.Product;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class Values {
    public static final String PRODUCT = "product";
    public static final String ID = "_id";
    public static final String USER = "user";

    public static final String NUM_PRODUCT = "num_product";
    public static final String PRODUCT_TITLE = "product_name";
    public static final String PRODUCT_CATEGORY = "id_category";
    public static final String PRODUCT_CATEGORY_GROUP = "category_group";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_DATE = "date";
    public static final String PRODUCT_CONTENT = "content";
    public static final String PRODUCT_STATUS = "status";
    public static final String PRODUCT_ADDRESS = "id_address";
    public static final String PRODUCT_ADDRESS_GROUP = "address_group";

    public static final String PRODUCT_PICTURE = "pictures";

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONENUMBER = "phoneNumber";
    public static final String USER_PASSWORD = "password";

//    public static String URL_SERVER = "http://192.168.1.61/datn";
    public static String URL_SERVER = "http://danghien.xyz/datn";
    public static String URL_SERVER_PICTURE = URL_SERVER + "/images/";
    public static String URL_GET_LIST = URL_SERVER + "/getAllProduct.php";
    public static String URL_GET_LIST_FILTER = URL_SERVER + "/filter.php";
    public static String URL_GET_DETAIL = URL_SERVER + "/getDetailProduct.php";
    public static String URL_DELETE = URL_SERVER + "/delete.php";
    public static String URL_SELL_PRODUCT = URL_SERVER + "/insert.php";

    public static void setUrlServer(String url) {
        URL_SERVER = url;
        URL_SERVER_PICTURE = URL_SERVER + "/images/";
        URL_GET_LIST = URL_SERVER + "/getAllProduct.php";
        URL_GET_LIST_FILTER = URL_SERVER + "/filter.php";
        URL_GET_DETAIL = URL_SERVER + "/getDetailProduct.php";
        URL_DELETE = URL_SERVER + "/delete.php";
        URL_SELL_PRODUCT = URL_SERVER + "/insert.php";

    }


    public static ArrayList<Address.AddressGroup> getAddressGroup() {
        ArrayList<Address.AddressGroup> addressGroups = new ArrayList<>();
        Address.AddressGroup addressGroup = new Address.AddressGroup(1, "Hà Nội");
        addressGroups.add(addressGroup);
        addressGroup = new Address.AddressGroup(2, "TP Hồ Chí Minh");
        addressGroups.add(addressGroup);
        addressGroup = new Address.AddressGroup(3, "Miền Trung");
        addressGroups.add(addressGroup);

        return addressGroups;
    }


    public static ArrayList<Category.CategoryGroup> getCategoryGroup() {
        ArrayList<Category.CategoryGroup> categoryGroups = new ArrayList<>();
        Category.CategoryGroup categoryGroup = new Category.CategoryGroup(1, "Công nghệ");
        categoryGroups.add(categoryGroup);
        categoryGroup = new Category.CategoryGroup(2, "Xe");
        categoryGroups.add(categoryGroup);
        categoryGroup = new Category.CategoryGroup(3, "Thời trang");
        categoryGroups.add(categoryGroup);
        return categoryGroups;
    }

//    public static ArrayList<Category> getCategories() {
//        ArrayList<Category> listCategory = new ArrayList<>();
//        Category category = new Category(1, "ĐTDĐ", new Category.CategoryGroup(1, "Công nghệ"));
//        listCategory.add(category);
//
//        category = new Category(2, "ĐTDĐ", new Category.CategoryGroup(1, "Công nghệ"));
//        listCategory.add(category);
//        category = new Category(3, "Máy tính bảng", new Category.CategoryGroup(1, "Công nghệ"));
//        listCategory.add(category);
//        category = new Category(4, "Xe máy", new Category.CategoryGroup(2, "Xe"));
//        listCategory.add(category);
//        category = new Category(5, "Xe oto", new Category.CategoryGroup(2, "Xe"));
//        listCategory.add(category);
//        category = new Category(6, "Xe đạp", new Category.CategoryGroup(2, "Xe"));
//        listCategory.add(category);
//        category = new Category(7, "Thời trang Nam", new Category.CategoryGroup(3, "Thời trang"));
//        listCategory.add(category);
//        category = new Category(8, "Thời trang Nữ", new Category.CategoryGroup(3, "Thời trang"));
//        listCategory.add(category);
//
//        return listCategory;
//
//    }

    public static Product getPoduct() {
        Product product;
        product = new Product();
        product.setName("Oppo R9");
        product.setDate(14587984524545l);
        product.setPrice(14520000l);
//        product.setPictures(new String[]{
//                "http://dash.coolsmartphone.com/wp-content/uploads/2016/02/20160222_090611_HDR.jpg",
//                "http://img.f8.sohoa.vnecdn.net/2016/03/17/MG-9409-1458225833_660x0.jpg",
//                "http://www.technobuffalo.com/wp-content/uploads/2015/09/iPhone-6s-arrives-early-3.jpg",
//                "http://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/htc-one-m9.jpg",
//                "http://dash.coolsmartphone.com/wp-content/uploads/2016/02/20160222_090611_HDR.jpg"
//        });
        product.setContent("Cửa hàng TOPPHONE tại số 8 ngõ 165 Chùa Bộc xin kính chào quý khách.\n" +
                "=========================\n" +
                "\n" +
                "- Cửa hàng cần bán iphone 5S 32GB Gold cửa nhà mạng Softbank, máy cực chất vỏ zin nguyên bản còn mới, màu vàng sang trọng đảm bảo ai nhìn cũng ưng luôn.\n" +
                "\n" +
                "- Máy zin 100% chưa bung máy sửa chữa gì, bao mở máy kiểm tra luôn cho quý khách yên tâm.\n" +
                "\n" +
                "- Có ảnh chụp máy tại cửa hàng.\n" +
                "\n" +
                "- Đang chạy ios 8.4 fix hết lỗi dùng như máy quốc tế, lắp sim các mạng viêtn nam dùng ngon lành.\n" +
                "\n" +
                "- Pin 5S còn rất bền, vào 3G wifi cả ngày cả đêm.\n" +
                "\n" +
                "Vì mình kinh doanh tại nhà riêng ko tốn nhiều chi phí nên giá thành cực kì cạnh tranh chỉ : 3.050.000\n" +
                "\n" +
                "* Hỗ trợ mua gói phụ kiện sạc, cáp, tai nghe giá 100K\n" +
                "* Bảo hành chuẩn phần cứng 3 tháng, 1 đổi 1 trong 7 ngày đầu nếu máy lỗi\n" +
                "* Bảo hành phần mềm 12 tháng, hỗ trợ trọn đời máy.\n" +
                "* Cài đặt ứng dụng và game miễn phí\n" +
                "======================\n" +
                "- Mình bán hàng nhập khẩu nguyên chiếc, đã được test kĩ mọi tính năng, chất luợng tốt quý khách mua về chỉ việc dùng mãi mãi, khác với hàng cũ kĩ lỗi bán lại nhé.\n" +
                "\n" +
                "- Nhân viên bán hàng nhiệt tình, trung thực, có hiểu biết chuyên sâu về các dòng máy của Aplle nên quý khách sẽ được hỗ trợ tối đa cả về phần cứng và phần mềm trọn đời máy.\n" +
                "\n" +
                "- Nhận thu mua lại máy đã qua sử dụng giá cao có thể.\n" +
                "\n" +
                "======================\n" +
                "\n" +
                "Liên hệ : Cửa hàng TOPPHONE\n" +
                "\n" +
                "Địa chỉ: Số 8 Ngõ 165 Chùa Bộc - đường vào sân bóng trường Đại Học Thủy Lợi, đường rộng ô tô đỗ thoải mái.\n" +
                "\n" +
                "Điện thoại : Số bên phải màn hình\n" +
                "\n" +
                "Thời gian bán hàng: 8h-22h hàng ngày\n" +
                "\n" +
                "Các bạn đánh \"topphone\" vào o tìm kiếm của chợ tốt để xem toàn bộ các sản phẩm chất lượng với giá rẻ thị trường hiện của TOPPHONE nhé\n" +
                "\n" +
                "=======================\n" +
                "\n" +
                "Ship hàng nội thành Hà Nội và các tỉnh trên toàn quốc.\n" +
                "\n" +
                "Giao lưu với iphone 5S iphone 6 iphone 6 plus samsung s3 samsung s4 samsung s5 samsung s6 htc m7 htc m8 htc m9 sony z sony z1 sony z2 sony z3");

        Category category = new Category();
        category.setName("Điện thoại di động");
        product.setId_category("1050");
        product.setStatus("80%");
//        User user = new User();
//        user.setName("TOP PHONE");
//        Address address = new Address();
//        address.setName("Hà Nội");
////        user.setAddress(address);
//        user.setPhoneNumber("0988446542");
//        product.setUser(user);
        return product;
    }

    public static ArrayList<Product> getListProduct() {
        ArrayList<Product> listProduct = new ArrayList<>();

        Product product = new Product();
        product.setName("Galaxy S6");
        product.setDate(145879845645l);
        product.setPrice(15000000l);
        product.setPictures("https://cdn2.vox-cdn.com/thumbor/PM_aE03DUJfdc2i3veNEsCYd7xo=/cdn0.vox-cdn.com/uploads/chorus_asset/file/6074451/samsung-galaxy-s7-hands-on-sean-okane17_2040.0.jpg");
        listProduct.add(product);

        product = new Product();
        product.setName("Oppo R9");
        product.setDate(14587984524545l);
        product.setPrice(14520000l);
        product.setPictures("http://img.f8.sohoa.vnecdn.net/2016/03/17/MG-9409-1458225833_660x0.jpg");
        listProduct.add(product);
        product = new Product();
        product.setName("Iphone 6s");
        product.setDate(145810845645l);
        product.setPrice(1000000l);
        product.setPictures("http://www.technobuffalo.com/wp-content/uploads/2015/09/iPhone-6s-arrives-early-3.jpg");
        listProduct.add(product);
        product = new Product();
        product.setName("HTC M9");
        product.setDate(145079845645l);
        product.setPrice(16300000l);
        product.setPictures("http://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/htc-one-m9.jpg");
        listProduct.add(product);
        product = new Product();
        product.setName("LG G5");
        product.setDate(145852845645l);
        product.setPrice(13800000l);
        product.setPictures("http://dash.coolsmartphone.com/wp-content/uploads/2016/02/20160222_090611_HDR.jpg");
        listProduct.add(product);
        product = new Product();
        product.setName("Oppo R9");
        product.setDate(14587984524545l);
        product.setPrice(14520000l);
        product.setPictures("http://img.f8.sohoa.vnecdn.net/2016/03/17/MG-9409-1458225833_660x0.jpg");
        listProduct.add(product);
        product = new Product();
        product.setName("Iphone 6s");
        product.setDate(145810845645l);
        product.setPrice(1000000l);
        product.setPictures("http://www.technobuffalo.com/wp-content/uploads/2015/09/iPhone-6s-arrives-early-3.jpg");
        listProduct.add(product);
        product = new Product();
        product.setName("HTC M9");
        product.setDate(145079845645l);
        product.setPrice(16300000l);
        product.setPictures("http://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/htc-one-m9.jpg");
        listProduct.add(product);
        return listProduct;


    }

}
