package com.example.arek.attranslate_ver10;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arek on 2014-12-21.
 */
public class CustomListViewAdapter extends ArrayAdapter<Word> {

    private Context context;
    private ArrayList<Word> words;

    public CustomListViewAdapter(Context context, ArrayList<Word> words) {
        super(context, R.layout.listview_item);
        this.context = context;
        this.words = words;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view ==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item,null);
            Log.i("info", "Po inflate");
        }

        TextView sourceLanguageWord = (TextView) view.findViewById(R.id.sourceLanguageWord);
        TextView translatedLanguageWord = (TextView) view.findViewById(R.id.translatedLanguageWord);

        Word word = words.get(position);

        sourceLanguageWord.setText(word.getSource());
        translatedLanguageWord.setText(word.getTranslated());

        return view;
    }

    @Override
    public int getCount() {
        return words.size();
    }
}
