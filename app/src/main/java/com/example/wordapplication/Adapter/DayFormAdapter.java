package com.example.wordapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.DaySense;
import com.example.wordapplication.R;

import java.util.List;

public class DayFormAdapter extends RecyclerView.Adapter<DayFormAdapter.ViewHolder> {
    private List<DaySense> mWord;
    //private List<Integer> path;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DaySense word = mWord.get(position);
        holder.daysense.setText(word.getWord());
        holder.daymean.setText(word.getInterpretation());
       // holder.dayImg.setImageResource(word.getImageId());

    }

    @Override
    public int getItemCount() {
        return mWord.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView daysense,daymean;
        //ImageView dayImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            daysense = (TextView) itemView.findViewById(R.id.day_sense);
            daymean = (TextView) itemView.findViewById(R.id.day_mean);
            //dayImg=(ImageView)itemView.findViewById(R.id.pic_address);
        }
    }

    public DayFormAdapter(List<DaySense> mWord) {
        this.mWord = mWord;
        //this.path=path;
    }
}
