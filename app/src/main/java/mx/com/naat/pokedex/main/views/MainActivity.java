package mx.com.naat.pokedex.main.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.favoritos.PokemonApp;
import mx.com.naat.pokedex.main.presenters.MainPresenter;
import mx.com.naat.pokedex.main.presenters.MainPresenterImplement;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements MainView{

    private ImageButton crashButton;
    private MainPresenter presenter;
    private PokemonApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        hideTopBar();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crashButton = findViewById(R.id.crash_button);
        presenter = new MainPresenterImplement((MainView) this);
    }

    public void doAppCenter() {
        String secretKey = "{9e3b4607-4727-444b-b857-b706327e1bc5}";
        presenter.appCenter(getApplication(), secretKey);
    }

    public void doCrashButton() {
        presenter.crashButton(crashButton);
    }

    public void doHttpInterceptorAndChuck() {
        String url = "https://pokeapi.co/api/v2/";
        presenter.httpInterceptorAndChuck(url, getApplicationContext(), MainActivity.this);
    }



    @Override
    public void hideTopBar() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}