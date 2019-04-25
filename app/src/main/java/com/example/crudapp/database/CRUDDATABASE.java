package com.example.crudapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.crudapp.model.EmployeeModel;

import java.util.ArrayList;

public class CRUDDATABASE {
    private CRUDDATABASE.DBHelper dbhelper;
    private final Context context;
    private SQLiteDatabase database;
    // db version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "crud_db";
    private static final String DATABASE_TABLE_EMPLOYEE = "crud_database";

    public static final String KEY_ROWID = "id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_HIRE_DATE = "hire_date";
    public static final String KEY_DEPARTMENT= "department";
    public static final String KEY_EMP_ID= "emp_id";

    private static class DBHelper extends SQLiteOpenHelper {

        @SuppressLint("NewApi")
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // create table to store msgs
            db.execSQL("CREATE TABLE " + DATABASE_TABLE_EMPLOYEE + " ("
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_FIRST_NAME + " TEXT, "
                    + KEY_LAST_NAME + " TEXT, "
                    + KEY_GENDER + " TEXT, "
                    + KEY_HIRE_DATE + " TEXT, "
                    + KEY_DEPARTMENT + " TEXT, "
                    + KEY_EMP_ID + " TEXT );");



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_EMPLOYEE);
            //db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_BASIC_INFORMATION);

            onCreate(db);
        }

    }
    // constructor
    public CRUDDATABASE(Context c) {
        context = c;


    }

    // open db
    public CRUDDATABASE open() {
        dbhelper = new  DBHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    // close db
    public void close() {
        dbhelper.close();
    }

    public long doRegister(String firstName, String lastName, String gender, String hireDate,String department,String empId){
        ContentValues cv = new ContentValues();
        cv.put(KEY_FIRST_NAME, firstName);
        cv.put(KEY_LAST_NAME,  lastName);
        cv.put(KEY_GENDER,  gender);
        cv.put(KEY_HIRE_DATE,  hireDate);
        cv.put(KEY_DEPARTMENT,  department);
        cv.put(KEY_EMP_ID,  empId);

        long dbInsert = database.insert(DATABASE_TABLE_EMPLOYEE, null, cv);

        if(dbInsert>0){
            Toast.makeText(context,"Data Add Successfully",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context,"Data Not Add Successfully",Toast.LENGTH_SHORT).show();
        }

        return dbInsert;

    }


    public void updateEmployee(String firstName, String lastName, String gender, String hireDate,String department,String empId){
        ContentValues cv = new ContentValues();
        cv.put(KEY_FIRST_NAME, firstName);
        cv.put(KEY_LAST_NAME,  lastName);
        cv.put(KEY_GENDER,  gender);
        cv.put(KEY_HIRE_DATE,  hireDate);
        cv.put(KEY_DEPARTMENT,  department);
        long updateDb = database.update(DATABASE_TABLE_EMPLOYEE, cv, " emp_id = " + empId , null);
        if(updateDb>0){
            Toast.makeText(context,"Data Update Successfully",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context,"Data Not Update Successfully",Toast.LENGTH_SHORT).show();
        }


    }


    public void removeAnEmployee(String empId){
         database.execSQL("delete from "+ DATABASE_TABLE_EMPLOYEE + " WHERE " + KEY_EMP_ID + " = '" + empId + "'" );

    }


    public ArrayList getAllEmployeeList(){

        ArrayList<EmployeeModel> empList = new ArrayList<>();

        //Tr: Modified
        String select_query = "SELECT * FROM " + DATABASE_TABLE_EMPLOYEE  ;

        Cursor cursor = database.rawQuery(select_query,null);

        // if(cursor != null && cursor.moveToFirst()){
        //int iDbId = cursor.getColumnIndex(KEY_ROWID);
        int iEmpName = cursor.getColumnIndex(KEY_FIRST_NAME);
        int iEmpLastName = cursor.getColumnIndex(KEY_LAST_NAME);
        int iGender = cursor.getColumnIndex(KEY_GENDER);
        int iDepartment = cursor.getColumnIndex(KEY_DEPARTMENT);
        int iEmpId = cursor.getColumnIndex(KEY_EMP_ID);



        for (cursor.moveToLast(); ! cursor.isBeforeFirst(); cursor.moveToPrevious()) {

            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setEmpName(cursor.getString(iEmpName) + cursor.getString(iEmpLastName));
            employeeModel.setGender(cursor.getString(iGender));
            employeeModel.setDepartment(cursor.getString(iDepartment));
            employeeModel.setEmpId(cursor.getString(iEmpId));
            empList.add(employeeModel);

        }
        cursor.close();
        return empList;
    }


}
