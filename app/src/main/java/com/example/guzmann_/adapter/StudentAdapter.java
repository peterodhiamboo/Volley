package com.example.guzmann_.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guzmann_.models.students;
import com.example.guzmann_.recycleview_volley.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.studentViewHolder> {

    private Context mcontext;
    private List<students> myStudents;

    public StudentAdapter(Context mcontext, List<students> myStudents) {
        this.mcontext = mcontext;
        this.myStudents = myStudents;
    }

    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.student_items, parent, false);
        return new studentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {
        students student = myStudents.get(position);

        holder.name.setText(student.getName());
        holder.age.setText(String.valueOf(student.getAge()));
        holder.rollno.setText(String.valueOf(student.getRollNo()));
        holder.course.setText(student.getCourse());


        Glide.with(mcontext)
                .load(student.getImageProf())
                .into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return myStudents.size();
    }

    static class studentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profileImage)
        ImageView profileImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.rollno)
        TextView rollno;
        @BindView(R.id.course)
        TextView course;

        studentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
