package com.metacodersbd.doctorhousedoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.doctorhousedoctor.model.modelForReq;
import com.metacodersbd.doctorhousedoctor.viewholders.viewHoldersForApointment;

import java.util.HashMap;

public class accpectedList extends AppCompatActivity {

    RecyclerView mrecyclerview  ;
    LinearLayoutManager linearLayoutManager ;
    DatabaseReference mref;

    FirebaseRecyclerOptions<modelForReq> options ;
    FirebaseRecyclerAdapter<modelForReq, viewHoldersForApointment> firebaseRecyclerAdapter ;

    View view  ;
    String uid = "TEST" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accpected_list);
        mref = FirebaseDatabase.getInstance().getReference("REQDB"); // db link

        uid = FirebaseAuth.getInstance().getUid() ;



        mrecyclerview =findViewById(R.id.recyverViewaacceptedreqList) ;

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());


        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        mrecyclerview.setLayoutManager(linearLayoutManager) ;
        mrecyclerview.setHasFixedSize(true);

        mref.keepSynced(true);

        loadDataToFireBase()  ;





    }

    private void loadDataToFireBase() {


        Query firebasaseQuery =  mref.orderByChild("docID").startAt(uid).endAt(uid+"\uf8ff");




        options = new FirebaseRecyclerOptions.Builder<modelForReq>().setQuery( firebasaseQuery , modelForReq.class).build() ;

        firebaseRecyclerAdapter  = new FirebaseRecyclerAdapter<modelForReq, viewHoldersForApointment>(options) {
            @NonNull
            @Override
            public viewHoldersForApointment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View iteamVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_my_appointment, parent, false);
                final viewHoldersForApointment viewholders = new viewHoldersForApointment(iteamVIew);







                return viewholders ;
            }

            @Override
            protected void onBindViewHolder(@NonNull viewHoldersForApointment viewHoldersForApointment, final int i, @NonNull modelForReq model) {

                viewHoldersForApointment.setDataToView(getApplicationContext() , model.getPname(),"Age : "+ model.getPage() ,model.getDocTime()

                        , model.getStats() , model.getCharge() , model.getAdate());



                viewHoldersForApointment.statstv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String postId = getItem(i).getPostid()  ;


                        mref.child(postId) ;
                        HashMap map = new HashMap();

                        map.put("stats" , "Accepted") ;
                        mref.child(postId).updateChildren(map);






                    }
                });



            }





        };
        mrecyclerview.setLayoutManager(linearLayoutManager) ;
        firebaseRecyclerAdapter.startListening();
        mrecyclerview.setAdapter(firebaseRecyclerAdapter);





    }
}
