package com.example.Nancy_C0771699_A3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_two extends AppCompatActivity {
    private Spinner spinner_candidate;
    ToggleButton conditions_button;
    Button vote;
    EditText voter_name, voter_id;
    private ArrayList<Candidate> candidate_list;
    ArrayList<Voter> voter_list;
    private boolean accepted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voter_activity);

        voter_list = new ArrayList<Voter>();
        spinner_candidate = findViewById(R.id.spinnerCandidate);
        conditions_button = findViewById(R.id.toggleConditions);
        vote = findViewById(R.id.voteButton);
        voter_name = findViewById(R.id.voterName);
        voter_id = findViewById(R.id.voterID);


        Intent intent = getIntent();
        ArrayList<Candidate> get_candidates = (ArrayList<Candidate>) intent.getSerializableExtra("candidates");
        candidate_list = get_candidates;
        ArrayAdapter<Candidate> adapter = new ArrayAdapter<Candidate>(this,
                android.R.layout.simple_spinner_item, candidate_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_candidate.setAdapter(adapter);


        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(voter_name.getText().toString().isEmpty()){
                    Toast.makeText(activity_two.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(voter_id.getText().toString().isEmpty()){
                    Toast.makeText(activity_two.this, "Please Enter a ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Voter V : voter_list) {
                    if(V.getId() == Integer.parseInt(voter_id.getText().toString())){
                        Toast.makeText(activity_two.this, "ID already present!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!conditions_button.isChecked()){
                    Toast.makeText(activity_two.this, "Please accept the terms and condition", Toast.LENGTH_SHORT).show();
                    return;
                }

                voter_list.add(new Voter(Integer.parseInt(voter_id.getText().toString()), voter_name.getText().toString()));
                int selectedCandidateIndex = spinner_candidate.getSelectedItemPosition();
                Candidate selectedCandidate = candidate_list.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(activity_two.this, "Vote casted !!", Toast.LENGTH_SHORT).show();


            }
        });

        conditions_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    conditions_button.setTextOn("Refuse Terms");

                } else {

                    conditions_button.setTextOff("Accept Terms");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(activity_two.this, MainActivity.class);
        intent.putExtra("candidates", candidate_list);
        startActivity(intent);
    }
}
