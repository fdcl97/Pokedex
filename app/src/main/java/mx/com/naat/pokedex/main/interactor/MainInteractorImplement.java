package mx.com.naat.pokedex.main.interactor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import java.io.IOException;

import mx.com.naat.pokedex.main.presenters.MainPresenter;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainInteractorImplement implements MainInteractor{
    private MainPresenter presenter;

    public MainInteractorImplement(MainPresenter mainPresenter) {
        this.presenter = mainPresenter;
    }

    @Override
    public void appCenter(Application application, String secretKey) {
        AppCenter.start(application, secretKey,
                Analytics.class, Crashes.class);

    }

    @Override
    public void crashButton(ImageButton crashButton) {
        crashButton.setOnClickListener(view -> {
            throw new RuntimeException("Soy un crash de Pokedex"); // Force a crash
        });
    }

    @Override
    public void httpInterceptorAndChuck(String url, Context context, Activity activity) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckerInterceptor(context))
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .certificatePinner(
                        new CertificatePinner.Builder()
                                .add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
                                .build())
                .build();

        Request request = new Request.Builder()
                .header("User-Agent", "OkHttp Example")
                .url(url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    response.body().close();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("HTTP RESPONSE: ", response.toString());
                    }
                });

            }
        });

    }

}
