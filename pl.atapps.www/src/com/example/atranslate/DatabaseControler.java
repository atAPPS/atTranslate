package com.example.atranslate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseControler extends SQLiteOpenHelper {

	public DatabaseControler(Context context){
		super(context, "Hasla.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table hasla(" + 
		        "nr integer primary key autoincrement," + 
				"haslo text," + 
		        "tlumaczenie text);" +
				"");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	

	public void addContent(String haslo, String tlumaczenie){
		SQLiteDatabase db = getWritableDatabase();
			ContentValues wartosci = new ContentValues();
			wartosci.put("haslo", haslo);
			wartosci.put("tlumaczenie", tlumaczenie);
			db.insertOrThrow("Hasla", null, wartosci);		
	}
	
	public Cursor giveAll() {
		String[] kolumny ={"nr","haslo","tlumaczenie"};
		SQLiteDatabase db = getReadableDatabase();
		Cursor kursor = db.query("Hasla", kolumny, null, null, null, null, null);
		return kursor;
	}
	
	public void deleteContent(int id){
		SQLiteDatabase db = getWritableDatabase();
		String[] args = {""+id};
		db.delete("hasla", "nr=?", args);
	}
	
	public void updateDatabase(int nr, String haslo, String tlumaczenie){
		SQLiteDatabase db = getWritableDatabase();
			ContentValues wartosci = new ContentValues();
			wartosci.put("haslo", haslo);
			wartosci.put("tlumaczenie", tlumaczenie);
			String args[]={nr+""};
			db.update("Hasla", wartosci, "nr=?", args);
	}
}
