package com.bot10101.miniprojet.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bot10101.miniprojet.Model.Question;
import com.bot10101.miniprojet.R;
import com.bot10101.miniprojet.Repo.QuestionRepo;
import com.bot10101.miniprojet.ViewModel.QuestionVm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    QuestionVm mvq;
    Button btn1;
    TextView txtv1;
    RadioButton rd1,rd2,rd3;
    Chronometer Crm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        txtv1=findViewById(R.id.txtv1);
        rd1=findViewById(R.id.rd1);
        rd2=findViewById(R.id.rd2);
        rd3=findViewById(R.id.rd3);
        Crm1=findViewById(R.id.Crm1);
        ArrayList<Integer> arrQ=new ArrayList<>();
        ArrayList<Integer> arrAn=new ArrayList<>();
        final int[] cmptQ = {0};

        mvq = new ViewModelProvider(MainActivity.this).get(QuestionVm.class);
        try {
            QuestionRepo.i = getResources().getAssets().open("Data.json");
        } catch (IOException e) {}


        int [] l={15,30,40};
        final boolean[] b = {false};

        Crm1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().equals("00:30")) {
                    int xRandom=getRandomNumber(cmptQ[0],arrQ);
                    if (xRandom!=0){
                        setQuestion(xRandom,cmptQ[0]);
                        arrAn.add(0);
                        chronometer.setText("00:00");
                        chronometer.setBase(SystemClock.elapsedRealtime());
                    }else{
                        arrAn.add(0);
                        NextPage(arrQ, arrAn);
                        Crm1.stop();
                        finish();
                    }

                }
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crm1.setBase(SystemClock.elapsedRealtime());

                if (cmptQ[0] ==0 && getCheckedRadio()!=0){
                        cmptQ[0] =l[getCheckedRadio()-1];
                    Crm1.start();
                }

                if(cmptQ[0]!=0){
                    int xRandom=getRandomNumber(cmptQ[0],arrQ);
                    if (xRandom!=0){
                        if (b[0]){
                            arrAn.add(getCheckedRadio());
                        }
                        setQuestion(xRandom,cmptQ[0]);

                        b[0] =true;
                    }else{
                        arrAn.add(getCheckedRadio());
                        NextPage( arrQ, arrAn);
                        Crm1.stop();
                        finish();


                    }

                }

            }
        });

    }
    public void NextPage(ArrayList<Integer> arrQ,ArrayList<Integer> arrAn){
        Intent intent = new Intent(MainActivity.this, Score.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrQ", arrQ);
        bundle.putSerializable("arrAn", arrAn);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void setQuestion(int xRandom,int cmptQ){
        mvq.returnData(xRandom).observe(MainActivity.this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {

                txtv1.setText(question.Question);
                rd1.setText(question.rep1);
                rd2.setText(question.rep2);
                rd3.setText(question.rep3);
            }
        });
    }
    public int getRandomNumber(int max, ArrayList<Integer> arr){
        while (true){
            if(arr.size()==max){
                return 0;
            }
            Random random = new Random();
            int randomInt = random.nextInt(40) + 1;
            boolean b= true;
            for(int a:arr){
                if(randomInt==a){
                    b=false;
                }
            }
            if(b){
                arr.add(randomInt);
                return randomInt;
            }
        }
    }
    public int getCheckedRadio(){
        if(rd1.isChecked()){
            return 1;
        }
        if(rd2.isChecked()){
            return 2;
        }
        if(rd3.isChecked()){
            return 3;
        }
        return 0;
    }
}