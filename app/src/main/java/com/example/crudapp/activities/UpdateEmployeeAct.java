package com.example.crudapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.crudapp.R;
import com.example.crudapp.database.CRUDDATABASE;

public class UpdateEmployeeAct extends AppCompatActivity {
    EditText edtFirstName,edtLastName,edtHireDate,edtDepartment,edtEmpId;
    RadioGroup rgGender;
    RadioButton rbtnMale,rbtnFemale;
    RadioButton selectedGender;
    Button btnUpdateEmp;
    CRUDDATABASE cruddatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
        initViews();
    }


    public void initViews(){
        edtFirstName= (EditText)findViewById(R.id.edit_text_first_name);
        edtLastName= (EditText)findViewById(R.id.edit_text_last_name);
        rgGender = (RadioGroup) findViewById(R.id.radio_gender);
        rbtnMale = (RadioButton)findViewById(R.id.radio_male);
        rbtnFemale = (RadioButton)findViewById(R.id.radio_female);
        edtHireDate = (EditText)findViewById(R.id.edit_text_hire_date);
        edtDepartment = (EditText)findViewById(R.id.edit_text_dept);
        btnUpdateEmp = (Button)findViewById(R.id.button_update_employee);
        edtEmpId = (EditText) findViewById(R.id.edit_text_emp_id);
        cruddatabase = new CRUDDATABASE(UpdateEmployeeAct.this);

        btnUpdateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                selectedGender = (RadioButton)findViewById(rgGender.getCheckedRadioButtonId());
                String gender = selectedGender.getText().toString();
                String hireDate = edtHireDate.getText().toString();
                String department= edtDepartment.getText().toString();
                String empId= edtEmpId.getText().toString();
                if(!(firstName.trim().length()>0)){
                    Toast.makeText(UpdateEmployeeAct.this,"Employee Id  required",Toast.LENGTH_SHORT).show();
                    return;
                }


                cruddatabase.open();
                cruddatabase.updateEmployee(firstName,lastName,gender,hireDate,department,empId);

                clearAllEdt();

            }
        });


    }


    public void clearAllEdt(){
        edtFirstName.setText("");
        edtLastName.setText("");
        edtHireDate.setText("");
        edtDepartment.setText("");

    }

}
