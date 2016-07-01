package fr.esgi.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nico on 14/06/16.
 */
public class ProfilFragment extends Fragment {

    public static final String MY_USER = "MyUser";
    @BindView(R.id.avatarUrl)
    CircleImageView avatarUrl;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.pseudo)
    TextView pseudo;

    User user;

    public static ProfilFragment newInstance(User myUser) {
        ProfilFragment myProfil = new ProfilFragment();
        Bundle args = new Bundle();
        args.putSerializable(MY_USER, myUser);
        myProfil.setArguments(args);
        return myProfil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        user = (User) this.getArguments().getSerializable(MY_USER);

        Glide.with(avatarUrl.getContext())
            .load(user.getAvatarUrl())
            .into(avatarUrl);
        pseudo.setText(user.getLogin());
        name.setText(user.getName());
    }
}
