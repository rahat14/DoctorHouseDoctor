package com.metacodersbd.doctorhousedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacodersbd.doctorhousedoctor.Login_Reg_Controller.signInPage;
import com.metacodersbd.doctorhousedoctor.model.getdoctorProfileModel;

import java.util.HashMap;

public class Doctor_Ac_Create extends AppCompatActivity {

    EditText nameIn , locIN , catIN , feein ;
    Button up ;
    String name , loc ,catgory , fee ,uid ;
    String locid ;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__ac__create);
        
        uid = FirebaseAuth.getInstance().getUid() ; 
        

        nameIn = findViewById(R.id.doc_name);
        locIN = findViewById(R.id.loc);
        catIN = findViewById(R.id.cat);
        feein = findViewById(R.id.fees);
        up = findViewById(R.id.Submitbtn);



        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                name = nameIn.getText().toString() ; 
                loc = locIN.getText().toString() ;
                catgory = catIN.getText().toString() ;
                fee = feein.getText().toString() ;
                locid = loc.toLowerCase() ;

                locid = org.apache.commons.lang3.StringUtils.replace(locid, " ", "");
                


                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(loc) && !TextUtils.isEmpty(catgory) &&
                        !TextUtils.isEmpty(fee))
                {
                    
                    uploadData(name, loc , catgory , fee) ; 
                    
                    
                    
                }
                else {
                    Toast.makeText(Doctor_Ac_Create.this , "Fill Up The Data Properly" , Toast.LENGTH_SHORT)
                            .show();

                    
                }




               







            }
        });



    }

    private void uploadData(final String name, final String loc, final String catgory, final String feee) {


        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("doctorProfile").child(uid);


        getdoctorProfileModel modl  = new getdoctorProfileModel(name , catgory , feee,  loc  , "nill" , uid);

        mref.setValue(modl).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                final DatabaseReference hospitalRef = FirebaseDatabase.getInstance().getReference("hospitalList").child(locid).child("doctorCategory")
                        .child(catgory);

                HashMap map = new HashMap();
                map.put("name", catgory) ;


                hospitalRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        hospitalRef.child("doctorList") ;


                        HashMap map = new HashMap();
                        map.put("name", name ) ;
                        map.put("time", "nill" ) ;
                        map.put("uid", uid ) ;
                        map.put("hospital", loc ) ;
                        map.put("fee", feee ) ;
                        map.put("category", catgory ) ;

                        hospitalRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent intent = new Intent(getApplicationContext()  , MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {


                                        Toast.makeText(getApplicationContext() , e.getMessage(), Toast.LENGTH_LONG)
                                                .show();


                                    }
                                }) ;







                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Toast.makeText(getApplicationContext() , e.getMessage(), Toast.LENGTH_LONG)
                                        .show();


                            }
                        }) ;
















            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Toast.makeText(getApplicationContext() , e.getMessage(), Toast.LENGTH_LONG)
                                .show();


                    }
                }) ;







    }
}
