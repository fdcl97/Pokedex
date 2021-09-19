package mx.com.naat.pokedex.main.presenters;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageButton;

public interface MainPresenter {
    void appCenter(Application application, String secretKey);
    void crashButton(ImageButton crashButton);
    void httpInterceptorAndChuck(String url, Context context, Activity activity);
    void hideTopBar();
}
