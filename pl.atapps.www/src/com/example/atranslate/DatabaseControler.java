package com.example.atranslate;

import java.util.LinkedList;
import java.util.List;

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
	

	public void addContent(Haslo haslo){
		SQLiteDatabase db = getWritableDatabase();
			ContentValues wartosci = new ContentValues();
			wartosci.put("haslo", haslo.getHaslo());
			wartosci.put("tlumaczenie", haslo.getTlumaczenie());
			db.insertOrThrow("Hasla", null, wartosci);		
	}
	
	public List<Haslo> giveAll() {
		List<Haslo> hasla = new LinkedList<Haslo>();
		String[] kolumny ={"nr","haslo","tlumaczenie"};
		SQLiteDatabase db = getReadableDatabase();
		Cursor kursor = db.query("Hasla", kolumny, null, null, null, null, null);
		while(kursor.moveToNext()){
			Haslo haslo = new Haslo();
			haslo.setNr(kursor.getLong(0));
			haslo.setHaslo(kursor.getString(1));
			haslo.setTlumaczenie(kursor.getString(2));
			hasla.add(haslo);
		}
		return hasla;
	}
	
	public void deleteContent(int id){
		SQLiteDatabase db = getWritableDatabase();
		String[] args = {""+id};
		db.delete("hasla", "nr=?", args);
	}
	
	public void updateContent(Haslo haslo){
		SQLiteDatabase db = getWritableDatabase();
			ContentValues wartosci = new ContentValues();
			wartosci.put("haslo", haslo.getHaslo());
			wartosci.put("tlumaczenie", haslo.getTlumaczenie());
			String args[]={haslo.getNr()+""};
			db.update("Hasla", wartosci, "nr=?", args);
	}
	
	public Haslo giveHaslo(int nr){
		Haslo haslo = new Haslo();
		SQLiteDatabase db = getReadableDatabase();
		String[] kolumny ={"nr","haslo","tlumaczenie"};
		String args[]={nr+""};
		Cursor kursor = db.query("Hasla", kolumny, "nr=?", args,null, null, null, null);
		if(kursor!=null){
			kursor.moveToFirst();
			haslo.setNr(kursor.getLong(0));
			haslo.setHaslo(kursor.getString(1));
			haslo.setTlumaczenie(kursor.getString(2));
		}
		return haslo;
	}
}
