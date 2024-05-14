package com.example.midterm2020671055;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class recyclerView extends RecyclerView.Adapter<recyclerView.BookViewHolder> {
    ArrayList<book> list;
    public recyclerView(ArrayList<book> list){
        this.list = list;
    }

    @NonNull
    @Override
    public recyclerView.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerView.BookViewHolder holder, int position) {
        holder.bTitle.setText(list.get(position).getbTitle());
        holder.people.setText(list.get(position).getPeople());
        holder.year.setText(list.get(position).getYear()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class BookViewHolder extends RecyclerView.ViewHolder{
        TextView bTitle;
        TextView people;
        TextView year;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bTitle = (TextView) itemView.findViewById(R.id.tv1);
            this.people = (TextView) itemView.findViewById(R.id.tv2);
            this.year = (TextView) itemView.findViewById(R.id.tv3);
        }
    }
}
