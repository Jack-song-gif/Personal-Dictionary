package com.example.wordapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.IrWord;
import com.example.wordapplication.R;

import java.util.List;

public class IrWordFormAdapter extends RecyclerView.Adapter<IrWordFormAdapter.ViewHolder> {
    private List<IrWord> mWord;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.word_form_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IrWord word = mWord.get(position);
        holder.name.setText(word.getProWord());
        holder.translate.setText(word.getComWord());
    }

    @Override
    public int getItemCount() {
        return mWord.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,translate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.word_name);
            translate = (TextView) itemView.findViewById(R.id.word_translate);
        }
    }

    public IrWordFormAdapter(List<IrWord> mWord) {
        this.mWord = mWord;
    }
}
