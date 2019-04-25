package com.example.crudapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.crudapp.R;
import com.example.crudapp.model.EmployeeModel;



import java.util.ArrayList;

/**
 * Created by Tarikul on 6/7/2018.
 */

public class EmployeeAdapter extends BaseAdapter {
    private ArrayList<EmployeeModel> mEmployeeList = new ArrayList<>();

    private LayoutInflater mInflater;
    Context mContext;


    public EmployeeAdapter(ArrayList<EmployeeModel> employeeList, Context context) {
        this.mEmployeeList = employeeList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
       // mcontacts = new ArrayList<>();

    }


    @Override
    public int getCount() {

        return mEmployeeList.size();
    }

    @Override
    public EmployeeModel getItem(int position) {
        try {
            if (mEmployeeList != null) {
                return mEmployeeList.get(position);
            } else {
                return null;
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position,  View convertView,  ViewGroup parent) {

        final ViewHolder holder;
        final EmployeeModel empItem = getItem(position);

        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = vi.inflate(R.layout.listview_item, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

            String empName = empItem.getEmpName();
            String gender = empItem.getGender();
            String department = empItem.getDepartment();
            String empId = empItem.getEmpId();

        holder.txvEmpName .setText(empName);
        holder.txvGender.setText(gender);
        holder.txvDepartment.setText(department);
        holder.txvEmpId.setText(empId);
        return convertView;
    }


    // /////////// //
    // ViewHolder //
    // ///////// //
    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.txvEmpName = (TextView) v.findViewById(R.id.txv_emp_name);
        holder.txvGender = (TextView) v.findViewById(R.id.txv_gender);
        holder.txvDepartment = (TextView) v.findViewById(R.id.txv_department);
        holder.txvEmpId = (TextView) v.findViewById(R.id.txv_emp_id);
        return holder;
    }
    private   class ViewHolder {
        TextView txvEmpName,txvGender,txvDepartment,txvEmpId;

    }



}
