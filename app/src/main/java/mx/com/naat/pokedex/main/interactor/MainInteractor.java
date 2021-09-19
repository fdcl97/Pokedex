package mx.com.naat.pokedex.main.interactor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageButton;

public interface MainInteractor {

    void appCenter(Application application, String secretKey);
    void crashButton(ImageButton crashButton);
    void httpInterceptorAndChuck(String url, Context context, Activity activity);

}
