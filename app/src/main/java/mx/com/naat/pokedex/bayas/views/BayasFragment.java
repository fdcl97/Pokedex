package mx.com.naat.pokedex.bayas.views;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BayasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BayasFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bayas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Nav controller
        final NavController navController = Navigation.findNavController(view);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        TabLayout.Tab tab = tabLayout.getTabAt(2);
        tabLayout.selectTab(tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        navController.navigate(R.id.pokemonFragment);
                        break;

                    case 1:
                        navController.navigate(R.id.favoritosFragment);
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