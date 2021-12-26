package com.example.mybook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlLiteDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final  String DATABASE_NAME = "Myapp";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context mCtx;
    SqlLiteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = context;
    }

    //get details from db
    public ArrayList<Model> getDetails(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Model> modelList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM app", null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                Model count = new Model(cursor.getInt(0), cursor.getString(1), cursor.getString(2) , cursor.getString(3));
                modelList.add(count);
            }
            cursor.close();
            db.close();
        }
        return modelList;
    }

    public void CopyDatabaseFromAssets() throws IOException {
        InputStream myInput = mCtx.getAssets().open(DATABASE_NAME);
        //path to the created db
        String outFileName = getDatabasePath();
        //if the path doesnt exists first. create it
        File f = new File(mCtx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        //open the db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer,0,length);
        }
        //close stream
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private static String getDatabasePath() {
        return mCtx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = mCtx.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()){
            try {
                CopyDatabaseFromAssets();
                Toast.makeText(mCtx, "Coppying success from assets folder", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null,
                SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
