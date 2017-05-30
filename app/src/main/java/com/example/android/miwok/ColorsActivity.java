package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static android.R.color.black;
import static android.R.color.white;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>(Arrays.asList(
                new Word("red", "weṭeṭṭi", R.drawable.color_red),
                new Word("green","chokokki", R.drawable.color_green),
                new Word("brown","ṭakaakki", R.drawable.color_brown),
                new Word("gray","ṭopoppi", R.drawable.color_gray),
                new Word("black","kululli", R.drawable.color_black),
                new Word("white","kelelli", R.drawable.color_white),
                new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow),
                new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow)
        ));



        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(adapter);
    }
}
