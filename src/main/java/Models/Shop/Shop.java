package Models.Shop;

import Models.Employee.EmployeeFormatter;

public class Shop {
    private String nameOfShop; //перечислины переменные для магазина, написаны только геттеры и сеттеры для них
    //никакого функционала класс не несет, грубо просто хранит в себе переменные
    private String address;
    private String specializationOfShop;
    private String nameOfDirector;

    public Shop(String nameOfShop, String address, String specializationOfShop, String nameOfDirector) {
        this.nameOfShop = nameOfShop;
        this.address = address;
        this.specializationOfShop = specializationOfShop;
        this.nameOfDirector = nameOfDirector;
    }

    public String getNameOfShop() {
        return nameOfShop;
    }

    public void setNameOfShop(String nameOfShop) {
        this.nameOfShop = nameOfShop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getSpecializationOfShop() {
        return specializationOfShop;
    }

    public void setSpecializationOfShop(String specializationOfShop) {
        this.specializationOfShop = specializationOfShop;
    }

    public String getNameOfDirector() {
        return nameOfDirector;
    }

    public void setNameOfDirector(String nameOfDirector) {
        this.nameOfDirector = nameOfDirector;
    }
    @Override
    public String toString(){
        return ShopFormatter.formatShop(this);
    }
}
