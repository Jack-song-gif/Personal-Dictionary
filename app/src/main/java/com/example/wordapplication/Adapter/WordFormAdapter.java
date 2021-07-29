package com.example.wordapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.R;
import com.example.wordapplication.Word;

import java.util.List;

public class WordFormAdapter extends RecyclerView.Adapter<WordFormAdapter.ViewHolder> {
    private List<Word> mWord;

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
        Word word = mWord.get(position);
        holder.name.setText(word.getWord());
        holder.translate.setText(word.getInterpretation());
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

    public WordFormAdapter(List<Word> mWord) {
        this.mWord = mWord;
    }
}
