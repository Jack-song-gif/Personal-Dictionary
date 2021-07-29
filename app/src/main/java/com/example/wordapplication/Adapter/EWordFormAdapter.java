package com.example.wordapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.R;
import com.example.wordapplication.entity.Eword;

import java.util.List;

public class EWordFormAdapter extends RecyclerView.Adapter<EWordFormAdapter.ViewHolder> {
    private List<Eword> mWord;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.eword_form_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Eword word = mWord.get(position);
        holder.eword.setText(word.getEword());
        holder.emean.setText(word.geteMeaning());
        holder.rootword.setText(word.getRootWord());
    }

    @Override
    public int getItemCount() {
        return mWord.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView eword,emean,rootword;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eword = (TextView) itemView.findViewById(R.id.word_eword);
            emean = (TextView) itemView.findViewById(R.id.word_emean);
            rootword=(TextView)itemView.findViewById(R.id.word_rootword);
        }
    }

    public EWordFormAdapter(List<Eword> mWord) {
        this.mWord = mWord;
    }
}
