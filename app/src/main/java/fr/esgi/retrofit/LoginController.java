package fr.esgi.retrofit;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nico on 14/06/16.
 */
public class LoginController extends AppCompatActivity {

    public static final String PSEUDO = "pseudo";

    @BindView(R.id.userlogin)
    TextView userlogin;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.profile_image)
    de.hdodenhof.circleimageview.CircleImageView profileImage;
    GitHubService service;
    User user;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        service = GithubWebService.get();

        final String pseudo = getIntent().getStringExtra(PSEUDO); //Corrections utiliser la constante PSEUDO
        loadUser(pseudo);
        user = (User) getIntent().getSerializableExtra("MyUser");

        // Initializing Toolbar and setting it as the actionbar
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.account:
                        Toast.makeText(getApplicationContext(), "Account Selected", Toast.LENGTH_SHORT).show();

//                        RelativeLayout rl = (RelativeLayout) findViewById(R.id.header);
//                        imageProfil = (CircleImageView) findViewById(R.id.profile_image);
//                        imageProfil.setsrc
//                        Glide.with(drawerLayout.getContext()).load(user.getAvatarUrl()).into(imageProfil);

                        username = (TextView) findViewById(R.id.username);
                        username.setText(user.getName());
                        userlogin = (TextView) findViewById(R.id.userlogin);
                        userlogin.setText(user.getLogin());

                        ProfilFragment profilFragment = ProfilFragment.newInstance(user);
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, profilFragment)
                            .commit();

                        return true;

                    case R.id.repo:
                        Toast.makeText(getApplicationContext(), "Repo Selected", Toast.LENGTH_SHORT).show();

                        ReposFragment reposFragment = ReposFragment.newInstance(user);
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, reposFragment)
                            .commit();

                        return true;
                    case R.id.infos:
                        Toast.makeText(getApplicationContext(), "Infos Selected", Toast.LENGTH_SHORT).show();

                        InfosFragment infosFragment = InfosFragment.newInstance(pseudo);
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, infosFragment)
                            .commit();

                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected Boolean loadUser(final String name) {
        service.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("retrofit", "failure" + t);
            }
        });
        return true;
    }
}
