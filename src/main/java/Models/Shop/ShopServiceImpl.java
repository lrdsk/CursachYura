package Models.Shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ShopServiceImpl implements ShopService {
    private ObjectMapper objectMapper;
    private OkHttpClient client;

    public ShopServiceImpl(ObjectMapper objectMapper, OkHttpClient client) {
        this.objectMapper = objectMapper;
        this.client = client;
    }

    @Override
    public CompletableFuture<Shop> getShop() {
        CompletableFuture<Shop> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/shop")
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                future.complete(objectMapper.readValue(json,Shop.class));
            }
        });
        return future;
    }

    @Override
    public void setShop(Shop shop) {

    }
}
