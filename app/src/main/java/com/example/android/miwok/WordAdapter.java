package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yhous on 18/05/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColor;
    public WordAdapter (Activity context, ArrayList<Word> Word, int color) {
        super(context, 0, Word);
        mColor = color;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View ListItemView = convertView;
        if(ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord =  getItem(position);

        TextView defaultTextView = (TextView) ListItemView.findViewById(R.id.default_text_view);
        TextView miwokTextView = (TextView) ListItemView.findViewById(R.id.miwok_text_view);
        ImageView ressourceId = (ImageView) ListItemView.findViewById(R.id.image);


        View linearLayoutColor =  ListItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(), mColor);

        linearLayoutColor.setBackgroundColor(color);
        defaultTextView.setText(currentWord.getDefaultTranslation());
        miwokTextView.setText(currentWord.getMiwokTranslation());

        if(currentWord.hasImage()){
            ressourceId.setImageResource(currentWord.getRessourceId());
            ressourceId.setVisibility(View.VISIBLE);
        }else {
            ressourceId.setVisibility(View.GONE);
        }



        return ListItemView;
    }
}
