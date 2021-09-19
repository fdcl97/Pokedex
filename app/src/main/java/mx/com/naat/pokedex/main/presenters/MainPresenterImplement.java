package mx.com.naat.pokedex.main.presenters;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageButton;

import mx.com.naat.pokedex.main.interactor.MainInteractor;
import mx.com.naat.pokedex.main.interactor.MainInteractorImplement;
import mx.com.naat.pokedex.main.views.MainView;

public class MainPresenterImplement implements MainPresenter{

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImplement(MainView view) {
        this.view = view;
        this.interactor = new MainInteractorImplement(this);
    }

    @Override
    public void appCenter(Application application, String secretKey) {
        if (interactor != null) {
            interactor.appCenter(application, secretKey);
        }
    }

    @Override
    public void crashButton(ImageButton crashButton) {
        if (interactor != null) {
            interactor.crashButton(crashButton);
        }
    }

    @Override
    public void httpInterceptorAndChuck(String url, Context context, Activity activity) {
        if (interactor != null) {
            interactor.httpInterceptorAndChuck(url, context, activity);
        }
    }

    @Override
    public void hideTopBar() {
        if (interactor != null) {
            view.hideTopBar();
        }
    }

}
