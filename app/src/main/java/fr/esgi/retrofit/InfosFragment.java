package fr.esgi.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nico on 17/06/16.
 */
public class InfosFragment extends Fragment {

    public static final String PSEUDO = "pseudo";

    public static InfosFragment newInstance(String userName){
        InfosFragment myRepos = new InfosFragment();
        Bundle args = new Bundle();
        args.putString(PSEUDO, userName);
        myRepos.setArguments(args);
        return myRepos;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.infos_fragment,container,false);
        Log.d("Create InfosFragment", "LOG");
        return v;
    }

}
