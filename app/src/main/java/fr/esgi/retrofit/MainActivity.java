package fr.esgi.retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.askpseudo)
    TextView askpseudo;
    @BindView(R.id.pseudo)
    EditText pseudo;
    @BindView(R.id.send)
    Button send;

    GitHubService service;
    User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        service = GithubWebService.get();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("MyUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("pseudo", pseudo.getText().toString());
                editor.commit();


                Intent intent = new Intent(MainActivity.this, LoginController.class);
                intent.putExtra("pseudo", pseudo.getText().toString());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        askpseudo.setText("Entrez votre pseudo :");
        send.setText("Valider");

        SharedPreferences prefs = getSharedPreferences("MyUser", MODE_PRIVATE);
        String userPseudo = prefs.getString("pseudo", "");

        if (userPseudo != "") {
            pseudo.setText(userPseudo);
        }

//        displayUser(null);
    }

/*    protected Boolean loadUser(final String name){
        Log.d("IN LOADUSER "+name, "ACTIVITY");
        service.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("IN onResponse " + name, "ACTIVITY");
                User user = response.body();
                displayUser(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("retrofit", "failure" + t);
            }
        });
        return true;
    }

    protected void displayUser(User user){
        askpseudo.setText("Entrez votre pseudo :");
        send.setText("Valider");

        if(user != null) {
            Log.d("=> " + user.getLogin() + " " + user.getName() + " " + user.getFollowers(), "ACTIVITYDEOUF");
            myUser = new User(user.getName(), user.getLogin(), user.getAvatarUrl(), user.getFollowers());
            Log.d("=> " + myUser.getLogin() + " " + myUser.getName() + " " + myUser.getFollowers(), "ACTIVITYDEOUF");
        }

        SharedPreferences prefs = getSharedPreferences("MyUser", MODE_PRIVATE);
        String userPseudo = prefs.getString("pseudo", "");

        if (userPseudo != "") {
            pseudo.setText(userPseudo);

        }
    }*/
}