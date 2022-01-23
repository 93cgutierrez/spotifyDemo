package co.demo.spotifydemo.io;

import android.content.Context;
import android.content.ContextWrapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import co.demo.spotifydemo.util.PreferenceUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceInterceptor extends ContextWrapper implements Interceptor {
    private final Context context;

    public ServiceInterceptor(Context base) {
        super(base);
        this.context = base;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request newRequest = chain.request();
        if (newRequest.header("No-Authentication") == null) {
            String finalToken = "Bearer " + PreferenceUtil.getToken(context);
            newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build();
        }
        return chain.proceed(newRequest);
    }

}
