package com.example.zadaa1orwma;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity {
    TextView name1,name2,name3;
    TextView person1, person2, person3;
    ImageButton ib1,ib2,ib3;
    Button bInspiration, bDescription;
    EditText et_description;
    RadioGroup rb_choose_person;
    RadioButton radio1,radio2, radio3;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeUI();
    }

    private void InitializeUI(){
        this.name1=(TextView)findViewById(R.id.name1);
        this.name2=(TextView)findViewById(R.id.name2);
        this.name3=(TextView)findViewById(R.id.name3);

        this.person1=(TextView)findViewById(R.id.person1_bio);
        this.person2=(TextView)findViewById(R.id.person2_bio);
        this.person3=(TextView)findViewById(R.id.person3_bio);

        this.ib1=(ImageButton)findViewById(R.id.ib1);
        this.ib2=(ImageButton)findViewById(R.id.ib2);
        this.ib3=(ImageButton)findViewById(R.id.ib3);

        this.bInspiration=(Button) findViewById(R.id.bInspiration);
        this.bDescription=(Button) findViewById(R.id.bDescription);

        this.rb_choose_person=(RadioGroup) findViewById(R.id.rb_choose_person);
        this.radio1=(RadioButton) findViewById(R.id.radio1);
        this.radio2=(RadioButton) findViewById(R.id.radio2);
        this.radio3=(RadioButton) findViewById(R.id.radio3);

        this.et_description=(EditText)findViewById(R.id.et_description);
    }
    public void customOnClick_Inspiration(View view){
        final int selected = new Random().nextInt(3)+1;
        String tekst="";
        String ispis="";

        switch(selected) {
            case (1):
                ispis=name1.getText().toString();
                tekst = person1.getText().toString();
                break;
            case (2):
                ispis=name2.getText().toString();
                tekst = person2.getText().toString();
                break;
            case (3):
                ispis=name3.getText().toString();
                tekst = person3.getText().toString();
                break;
        }
        ispis+=": ";
        Choose_random_sentence(tekst, ispis);
    }

    public void customOnClick_Description_change(View view){
        if(et_description.getText().toString().length()<10) {
            Toast.makeText(this, "Novi opis ima manje od 10 znakova", Toast.LENGTH_LONG).show();
        }
        else {
           switch (rb_choose_person.getCheckedRadioButtonId()) {
               case R.id.radio1:
                   person1.setText(et_description.getText());
                   break;
               case R.id.radio2:
                   person2.setText(et_description.getText());
                   selected = 2;
                   break;
               case R.id.radio3:
                   person3.setText(et_description.getText());
                   selected = 3;
                   break;
           }
       }
    }



    void Choose_random_sentence(String text, String rezultat){
        if(text=="") return;
        //ovo je malo clumsy, inace bi radio sa pokazivacima, al novi mi je jezik tako da nisam
        int broj_recenica=Counting(text, '.');
        final int random = new Random().nextInt(broj_recenica);
//
        int counter=0;
        for(int i=0;i<text.length();i++) {
            if(counter==random && text.charAt(i)!='.') rezultat+=text.charAt(i);
            if(text.charAt(i)=='.') counter++;
        }
        Toast.makeText(this, rezultat, Toast.LENGTH_LONG).show();
    }

    int Counting(String S, char x){
        //trebao bi mu predati listu charova ('.', enter, ...)
        int rezultat=0;
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)==x){
                rezultat++;
            }
        }
        if(rezultat==0) rezultat=1;
        return rezultat;
    }
}