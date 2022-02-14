package com.hfad.myaddressbook.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.myaddressbook.EmployeeDetailsActivity;
import com.hfad.myaddressbook.R;
import com.hfad.myaddressbook.model.Employees;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Employees> employee;

    public MyAdapter(ArrayList<Employees> employee){
        this.employee = employee;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflat = LayoutInflater.from(parent.getContext());
        View view = inflat.inflate(R.layout.my_employee, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(holder.employeeImg.getContext()).load(employee.get(position)
                .getPicture().getThumbnail()).into(holder.employeeImg);
        holder.employeeName.setText(employee.get(position).getName().getFirst() +
                " " + employee.get(position).getName().getLast());
        holder.employeeCity.setText("City: " + employee.get(position).getLocation()
        .getCity() + ", " + employee.get(position).getLocation().getCountry());

        String phone = employee.get(position).getPhone();
        String phoneReg = phone.replaceAll("[-()]", "");
        String cell = employee.get(position).getCell();
        String cellReg = cell.replaceAll("[-()]", "");
        holder.employeePhone.setText("Phone: " + cellReg + " / " + phoneReg);

        String member = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .parse(employee.get(position).getRegistered().getDate());
            DateFormat formater = new SimpleDateFormat("MMMM yyyy");
            String result = formater.format(date);
            member = "Member since: " + result;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.employeeMember.setText(member);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EmployeeDetailsActivity.class);
                intent.putExtra("empName", employee.get(position).getName().getFirst()
                + " " + employee.get(position).getName().getLast());
//                intent.putExtra("mapFragment", employee.get(position).getLocation().getCoordinate().getLatitude()
//                + employee.get(position).getLocation().getCoordinate().getLongitude());
                intent.putExtra("empCity", "City: " + employee.get(position).getLocation().getCity()
                + ", " + employee.get(position).getLocation().getCountry());
                String phone = employee.get(position).getPhone();
                String phoneReg = phone.replaceAll("[-()]", "");
                String cell = employee.get(position).getCell();
                String cellReg = cell.replaceAll("[-()]", "");
                intent.putExtra("empPhone", "Phone: " + cellReg + " / " + phoneReg);
                String member = "";
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                            .parse(employee.get(position).getRegistered().getDate());
                    DateFormat formater = new SimpleDateFormat("MMMM yyyy");
                    String result = formater.format(date);
                    member = "Member since: " + result;
                } catch (ParseException e){
                    e.printStackTrace();
                }
                intent.putExtra("empMem", member);
                intent.putExtra("empEmail", employee.get(position).getEmail());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView employeeImg;
        TextView employeeName, employeeCity, employeePhone, employeeMember;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeImg = itemView.findViewById(R.id.employeeImg);
            employeeName = itemView.findViewById(R.id.employeeName);
            employeeCity = itemView.findViewById(R.id.employeeCity);
            employeePhone = itemView.findViewById(R.id.employeePhone);
            employeeMember = itemView.findViewById(R.id.employeeMember);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
