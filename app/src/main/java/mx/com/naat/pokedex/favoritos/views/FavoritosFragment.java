package mx.com.naat.pokedex.favoritos.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.favoritos.PokemonApp;

public class FavoritosFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Nav controller
        final NavController navController = Navigation.findNavController(view);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tabLayout.selectTab(tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        navController.navigate(R.id.pokemonFragment);
                        break;

                    case 2:
                        navController.navigate(R.id.bayasFragment);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}