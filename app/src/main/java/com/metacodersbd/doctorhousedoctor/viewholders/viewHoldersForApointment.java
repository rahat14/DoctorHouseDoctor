package com.metacodersbd.doctorhousedoctor.viewholders;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metacodersbd.doctorhousedoctor.R;


public class viewHoldersForApointment extends RecyclerView.ViewHolder {
    View mview ;
  public   TextView statstv  , statcCancel;



    public viewHoldersForApointment(@NonNull View itemView) {

        super(itemView);

        mview = itemView ;




    }
    public   void setDataToView(Context context , String  dname , String dloc , String dtime , String stats , String fee  ,String date    ){

        TextView nametv = mview.findViewById(
                R.id.Name);
        TextView feetv = mview.findViewById(R.id.fee);
        TextView timetv = mview.findViewById(R.id.time);
        TextView datetv = mview.findViewById(R.id.date);
        TextView loc = mview.findViewById(R.id.loc);
         statstv = mview.findViewById(R.id.stats);
         statcCancel = mview.findViewById(R.id.statscancel);





        //     Picasso.get().load(link).into(imageView);

        nametv.setText(dname);

        feetv.setText(fee);

        loc.setText(dloc);

        timetv.setText(dtime);
        datetv.setText(date);
        if(stats.contains("pending")){

            statstv.setText("Click To Accept");

            statstv.setTextColor(Color.RED);

        }
        else {


            statcCancel.setVisibility(View.INVISIBLE);
            statstv.setTextColor(Color.GREEN);
        }

    //    statstv.setText(stats);



    }






}
