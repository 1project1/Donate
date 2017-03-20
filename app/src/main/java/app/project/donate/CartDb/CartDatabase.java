package app.project.donate.CartDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ArupPc on 19-03-2017.
 */

public class CartDatabase extends SQLiteOpenHelper{

    SQLiteDatabase sqLiteDatabase;
    public CartDatabase(Context context) {
        super(context, "CART_DB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTb = "create table cart_items (id integer primary key autoincrement, title varchar, quantity integer)";
        sqLiteDatabase.execSQL(createTb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart_items");
        onCreate(sqLiteDatabase);
    }

    public void addData(String title, int qty){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("quantity",qty);
        sqLiteDatabase.insert("cart_items",null,cv);

    }

    public Cursor getData(){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query("cart_items",new String[]{"title","quantity"},null,null,null,null,null);
        return  c;
    }

    public void deleteAllData(){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart_items");
        onCreate(sqLiteDatabase);

    }
    public boolean deleteTitle(String name)
    {
        return sqLiteDatabase.delete("CART_DB", "title" + "=" + name, null) > 0;
    }

}
