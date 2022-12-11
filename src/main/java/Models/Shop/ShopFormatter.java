package Models.Shop;

public class ShopFormatter {
    private ShopFormatter(){
    }

    public static String formatShop(Shop shop){
        final StringBuilder sb = new StringBuilder();
        sb
                .append("Name shop: ")
                .append(shop.getNameOfShop())
                .append(", Address: ")
                .append(shop.getAddress())
                .append(", Specialization: ")
                .append(shop.getSpecializationOfShop())
                .append(", Director: ")
                .append(shop.getNameOfDirector());
        return sb.toString();
    }
}
