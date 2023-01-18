package Models.Shop;

import java.util.concurrent.CompletableFuture;

public interface ShopService {
    CompletableFuture<Shop> getShop();

    void setShop(Shop shop);
}
