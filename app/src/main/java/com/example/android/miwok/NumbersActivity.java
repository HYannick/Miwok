package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.android.miwok.R.id.rootView;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        ArrayList<Word> words = new ArrayList<Word>(Arrays.asList(
                new Word("one", "lutti"),
                new Word("two","otiiko"),
                new Word("three","tolookosu"),
                new Word("four","oyyisa"),
                new Word("five","massokka"),
                new Word("six","temmokka"),
                new Word("seven","kenekaku"),
                new Word("eight","kawinta"),
                new Word("nine","wo’e"),
                new Word("ten","na’aacha")
        ));



        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(adapter);
    }
}
