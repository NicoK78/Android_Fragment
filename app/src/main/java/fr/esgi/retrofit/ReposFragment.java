package fr.esgi.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nico on 17/06/16.
 */
public class ReposFragment extends Fragment {

    User user;
    List<Repo> repos;

    GitHubService service;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public static ReposFragment newInstance(User myUser){

        ReposFragment myRepos = new ReposFragment();

        Bundle args = new Bundle();
        args.putSerializable("MyUser", myUser);
        myRepos.setArguments(args);
        return myRepos;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.repos_fragment,container,false);

        ButterKnife.bind(this, v);

        user = (User) this.getArguments().getSerializable("MyUser");
        loadRepos(user.getLogin());

        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected Boolean loadRepos(final String name) {
        service = GithubWebService.get();
        service.getRepos(name).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                repos = response.body();

                for (final Repo aRepo : repos) {
                    Log.d(aRepo.getName(), "LOG");
                }

                myAdapter = new MyAdapter();
                recyclerView.setAdapter(myAdapter);

                myAdapter.addObjects(repos);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("retrofit", "failure" + t);
            }
        });
        return true;
    }
}
