package com.example.arek.attranslate_ver10;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class TranslateActivity extends Activity {

    static private Spinner leftSpinner;
    static private Spinner rightSpinner;

    static private Button mainTranslateButton;
    static private EditText mainEditText;

    private TextView testTextView;

    private ArrayAdapter<CharSequence> languageAdapter;

    private ListView wordsListView;
    private ArrayAdapter<CharSequence> wordsAdapter;
    private CustomListViewAdapter customListViewAdapter;

    private ArrayList<Word> wordDatabase;

    private TranslatePresenter translatePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        initTranslatePresenter();
        initTexts();
        initButton();
        initSpinners();
        initDatabase();
        customListViewAdapter = new CustomListViewAdapter(this,wordDatabase);
        testListView();
    }

    void initDatabase() {
        wordDatabase = new ArrayList<Word>();
        Word word1 = new Word("Kot","Cat");
        Word word2 = new Word("Pies","Dog");
        Word word3 = new Word("Zielony", "Green");
        wordDatabase.add(word1);
        wordDatabase.add(word2);
        wordDatabase.add(word3);
    }

    void initTranslatePresenter(){
        translatePresenter = new TranslatePresenter(this);
    }

    void initTexts(){
        mainEditText = (EditText) findViewById(R.id.mainEditText);
        mainEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                translatePresenter.onEditTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        testTextView = (TextView) findViewById(R.id.testTextView);
    }

    //initialization - Translate Button

    void initButton() {
        mainTranslateButton = (Button) findViewById(R.id.mainTranslateButton);
        mainTranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translatePresenter.onMainTranslateButtonClicked();
            }
        });
    }

    void initSpinners() {
        leftSpinner = (Spinner) findViewById(R.id.leftSpinner);
        rightSpinner = (Spinner) findViewById(R.id.rightSpinner);
        initSpinnerAdapters();
        initSpinnerListeners();
    }

    void initSpinnerAdapters() {
        languageAdapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(languageAdapter);
        rightSpinner.setAdapter(languageAdapter);
    }

    void initSpinnerListeners() {

        //spinnerListener that handles both spinner clicks

        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Spinner selectedSpinner;
                selectedSpinner = (Spinner) parent;
                if(selectedSpinner.getId() == R.id.leftSpinner) {
                    translatePresenter.onLeftSpinnerItemSelected(position);
                }

                if(selectedSpinner.getId() == R.id.rightSpinner) {
                    translatePresenter.onRightSpinnerItemSelected(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        //associate both spinners with one adapter, implement above

       leftSpinner.setOnItemSelectedListener(spinnerListener);
       rightSpinner.setOnItemSelectedListener(spinnerListener);
    }

    // testListView in order to check whether it is correctly implemented

    void testListView() {
        wordsListView = (ListView) findViewById(R.id.wordsListView);
        wordsListView.setAdapter(customListViewAdapter);

        Log.i("info","Enter testListView");
    }

    void setTestTextView(String word)
    {
        testTextView.setText(word);
    }
}
