package vn.monpay.monchat.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLOHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "monchat";       // Database Name
        private static final String TABLE_NAME = "SessionInfo";         // Table Name
        private static final int DATABASE_Version = 1;                  // Database Version
        private static final String UID = "_id";                        // Column I (Primary Key)
        private static final String NAME = "Name";                      //Column II
        private static final String MyPASSWORD= "Password";             // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" NVARCHAR(255) ,"+ MyPASSWORD+" NVARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public SQLOHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }

