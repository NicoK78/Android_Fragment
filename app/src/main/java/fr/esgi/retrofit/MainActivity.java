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

    public static final String MY_USER = "MyUser";
    public static final String PSEUDO = "pseudo";
    @BindView(R.id.askpseudo)
    TextView askpseudo;
    @BindView(R.id.pseudo)
    EditText pseudo;
    @BindView(R.id.send)
    Button send;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(MY_USER, Context.MODE_PRIVATE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Correction inline & .apply()
                sharedpreferences.edit()
                    .putString(PSEUDO, pseudo.getText().toString())
                    .apply();

                Intent intent = new Intent(MainActivity.this, LoginController.class);
                intent.putExtra(LoginController.PSEUDO, pseudo.getText().toString()); //Correction sontante LoginController.PSEUDO
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        askpseudo.setText("Entrez votre pseudo :");
        send.setText("Valider");

        String userPseudo = sharedpreferences.getString(PSEUDO, "");

        if (!"".equals(userPseudo)) { //Correction pas de != sur les strings
            pseudo.setText(userPseudo);
        }

    }
}