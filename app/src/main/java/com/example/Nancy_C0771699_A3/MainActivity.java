package com.example.Nancy_C0771699_A3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Candidate> candidateList;
    private TextView can1, can2, can3;
    private Button vote_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        can1 = findViewById(R.id.txtViewCandidate1);
        can2 = findViewById(R.id.txtViewCandidate2);
        can3 = findViewById(R.id.txtViewCandidate3);

        vote_button = findViewById(R.id.voting_button);

        candidateList = new ArrayList<Candidate>();
        Intent intent = getIntent();

        ArrayList<Candidate> candidates = (ArrayList<Candidate>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateList.add(new Candidate(1,"Candidate 1",0));
            candidateList.add(new Candidate(2,"Candidate 2",0));
            candidateList.add(new Candidate(3,"Candidate 3",0));
        }
        else{
            candidateList = candidates;
        }

        can1.setText(candidateList.get(0).getName()+" : " + candidateList.get(0).getVotes());
        can2.setText(candidateList.get(1).getName()+" : " + candidateList.get(1).getVotes());
        can3.setText(candidateList.get(2).getName()+" : " + candidateList.get(2).getVotes());

        vote_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_two.class);
                intent.putExtra("candidates", candidateList);
                startActivity(intent);
            }
        });


    }
}