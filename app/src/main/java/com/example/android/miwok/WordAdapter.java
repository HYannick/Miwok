package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yhous on 18/05/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter (Activity context, ArrayList<Word> Word) {
        super(context, 0, Word);
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

        defaultTextView.setText(currentWord.getDefaultTranslation());
        miwokTextView.setText(currentWord.getMiwokTranslation());


        return ListItemView;
    }
}
