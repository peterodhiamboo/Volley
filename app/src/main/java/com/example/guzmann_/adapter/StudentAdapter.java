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
        View view = LayoutInflater.from(mcontext).inflate(R.layout.student_items, parent, false);
        return new studentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {
        students student = myStudents.get(position);

        holder.studentName.setText(student.getName());
        holder.studentAge.setText(String.valueOf(student.getAge()));
        holder.rollNo.setText(String.valueOf(student.getRollNo()));
        holder.course.setText(student.getCourse());


        Glide.with(mcontext)
                .load(student.getImageProf())
                .into(holder.studentImage);
    }

    @Override
    public int getItemCount() {
        return myStudents.size();
    }

    public static class studentViewHolder extends RecyclerView.ViewHolder{

        private ImageView studentImage;
        private TextView studentName;
        private TextView studentAge;
        private TextView rollNo;
        private TextView course;

        public studentViewHolder(View itemView) {
            super(itemView);

            studentImage = itemView.findViewById(R.id.profileImage);
            studentName = itemView.findViewById(R.id.name);
            studentAge = itemView.findViewById(R.id.age);
            rollNo = itemView.findViewById(R.id.rollno);
            course = itemView.findViewById(R.id.course);
        }
    }
}
