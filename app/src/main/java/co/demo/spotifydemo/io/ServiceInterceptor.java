package co.demo.spotifydemo.io;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import co.demo.spotifydemo.util.Parameters;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request newRequest = chain.request();
        if (newRequest.header("No-Authentication") == null) {
            String finalToken = "Bearer " + Parameters.API_TOKEN;
            newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build();
        }
        return chain.proceed(newRequest);
    }

}
