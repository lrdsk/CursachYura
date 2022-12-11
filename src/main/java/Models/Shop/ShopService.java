package Models.Shop;

import Database.MyDateBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopService { //класс для взаимодействия с шопом, он берет все, что содержит шоп и работает с его переменными
    private MyDateBase dateBase = null;  //база данных для подключения к ней
    private Shop shop = null; //класс шоп из которого мы будем брать переменные
    public ShopService(MyDateBase dateBase,Shop shop){
        this.dateBase = dateBase;
        this.shop = shop;
    }
    private Shop mapShop(ResultSet result) throws SQLException {
        String shop_name = result.getString("shop_name");
        String address = result.getString("address");
        String specialization = result.getString("specialization");
        String shop_namedirector = result.getString("shop_namedirector");

        return new Shop(shop_name,address,specialization,shop_namedirector);
    }
    public Shop getInfoAboutShopFromBase() throws SQLException { //берет всю информацию о магазине из таблицы в постгресе
        PreparedStatement statement = dateBase.getConnection().prepareStatement("SELECT * FROM shop");
        ResultSet result = statement.executeQuery(); //выполняет запрос из того, что написано в переменной sql

        result.next();
        return this.mapShop(result);
    }
    public void setInfoAboutShopToBase(String name, String address, String specialization, String nameOfDirector) throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement("Update shop set shop_name = ?, " +
                "address = ?, specialization = ?, shop_namedirector = ? where shop_id = 1"
        );
        statement.setString(1, name);
        statement.setString(2, address);
        statement.setString(3, specialization);
        statement.setString(4, nameOfDirector);
        statement.executeUpdate();
    }
}
