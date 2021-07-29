package com.example.wordapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.R;
import com.example.wordapplication.entity.Pword;

import java.util.List;

public class NoteFormAdapter extends RecyclerView.Adapter<NoteFormAdapter.ViewHolder> {
    private List<Pword> mWord;
    private PopupMenu popupMenu;
    //private List<Integer> path;
    private AdapterView.OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.diary_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Pword word = mWord.get(position);
        holder.diaryNote.setText(word.getMysaying());
        holder.diaryTime.setText(word.getDate());
       // holder.dayImg.setImageResource(word.getImageId());
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(v.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.diary_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.diary_to_save:
                                //Toast.makeText(v.getContext(),"保存",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.dairy_to_delete:
                                DataSupport.delete(Pword.class,position);
                                break;
                            case R.id.dairy_to_modify:
                                Pword pword=new Pword();

                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mWord.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView diaryNote,diaryTime;
        //ImageView dayImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diaryNote = (TextView) itemView.findViewById(R.id.diary_note);
            diaryTime = (TextView) itemView.findViewById(R.id.diary_time);
        }
    }

    public NoteFormAdapter(List<Pword> mWord) {
        this.mWord = mWord;
        //this.path=path;
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.listener=listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView,int position);
    }
}
