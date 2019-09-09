package com.metacodersbd.doctorhousedoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.metacodersbd.doctorhousedoctor.Login_Reg_Controller.signInPage;

public class MainActivity extends AppCompatActivity {

    CardView acc , pen , log ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        acc = findViewById(R.id.accepted) ;

        log = findViewById(R.id.logOut);



        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(getApplicationContext() , accpectedList.class) ;
                startActivity(io);



            }
        });



        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                  Intent io = new Intent(getApplicationContext() , signInPage.class) ;
                startActivity(io);

                finish();



            }
        });


    }
}
