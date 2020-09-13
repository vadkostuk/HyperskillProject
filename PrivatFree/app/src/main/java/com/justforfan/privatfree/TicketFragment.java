package com.justforfan.privatfree;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {

private int second = 3600;
private boolean isRunning= false;
private TextView textViewCounter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        Date currentDate = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        TextView textViewDate = (TextView)view.findViewById(R.id.textViewDate);
        TextView textViewTime   = (TextView)view.findViewById(R.id.textViewTime);
        textViewDate.setText(dateText);
        textViewTime.setText(timeText);


        textViewCounter = (TextView)view.findViewById(R.id.textViewCounter);
        textViewCounter.setVisibility(View.GONE);

        final Button button = (Button)view.findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                textViewCounter.setVisibility(View.VISIBLE);
                 runTimer();
            }
        });

        return view;
    }


    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             int hours  = second/3600;
                             int minutes = (second % 3600)/60;
                             int sec = second % 60;
                             String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,sec);
                             textViewCounter.setText(time);

                                 second--;

                             handler.postDelayed(this,1000);
                         }
                     }
        );
    }

}
