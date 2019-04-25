package com.example.crudapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.crudapp.R;
import com.example.crudapp.adapter.EmployeeAdapter;
import com.example.crudapp.database.CRUDDATABASE;
import com.example.crudapp.model.EmployeeModel;

import java.util.ArrayList;

public class ViewAllEmployeeAct extends AppCompatActivity {
    ArrayList<EmployeeModel> empList = new ArrayList<>();
    ListView listView;
    EmployeeAdapter employeeAdapter;
    CRUDDATABASE cruddatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
        initViews();
    }

    public void initViews(){
        listView = (ListView)findViewById(R.id.listview);
        cruddatabase = new CRUDDATABASE(ViewAllEmployeeAct.this);
        cruddatabase.open();
        empList = cruddatabase.getAllEmployeeList();

        employeeAdapter = new EmployeeAdapter(empList,ViewAllEmployeeAct.this);
        listView.setAdapter(employeeAdapter);


    }
}
