package night.outer.outer_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import night.outer.outer_app.Auth.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser Null_firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();



        updateUI(currentUser);
    }

    protected void updateUI(FirebaseUser currentUser){
        if(currentUser!=Null_firebase){
            setContentView(R.layout.activity_main);
        }
        else {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        }
    }
}
