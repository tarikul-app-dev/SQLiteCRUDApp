package com.example.crudapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudapp.R;
import com.example.crudapp.database.CRUDDATABASE;

public class MainActivity extends AppCompatActivity {
    Button btnAddEmployee,btnUpdateEmployee,btnRemoveEmployee,btnViewAllEmp;
    CRUDDATABASE cruddatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    public void initViews(){
        btnAddEmployee = (Button)findViewById(R.id.btn_add_employee);
        btnUpdateEmployee = (Button)findViewById(R.id.btn_update_employee);
        btnRemoveEmployee = (Button)findViewById(R.id.btn_remove_employee);
        btnViewAllEmp = (Button)findViewById(R.id.btn_view_all_employee);
        cruddatabase = new CRUDDATABASE(MainActivity.this);


        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AddEmployeeAct.class);
                startActivity(intent);

            }
        });

        btnUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,UpdateEmployeeAct.class);
                startActivity(intent);

            }
        });
        btnRemoveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getEmpIdAndRemoveEmp();

            }
        });

        btnViewAllEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ViewAllEmployeeAct.class);
                startActivity(intent);
            }
        });
    }


    public void getEmpIdAndRemoveEmp(){

        LayoutInflater li = LayoutInflater.from(this);
        View getEmpIdView = li.inflate(R.layout.dialog_get_emp_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_emp_id.xml to alertdialog builder
        alertDialogBuilder.setView(getEmpIdView);

        final EditText userInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cruddatabase.open();
                        cruddatabase.removeAnEmployee(userInput.getText().toString());
                        Toast t = Toast.makeText(MainActivity.this,"Employee removed successfully!", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }).create()
                .show();

    }
}
