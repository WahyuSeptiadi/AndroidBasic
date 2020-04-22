package com.newbie.dicoding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.newbie.dicoding.Activity.DetailActivity;
import com.newbie.dicoding.Activity.MainActivity;

import java.util.ArrayList;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private ArrayList<LanguageItem> mLanguageList;
    Context mContext;

    public static class LanguageViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageLg;
        public TextView mNameLg;
        public TextView mDeskripLg;

        public LanguageViewHolder(View itemView) {
            super(itemView);
            mImageLg = itemView.findViewById(R.id.imageLg);
            mNameLg = itemView.findViewById(R.id.nameLg);
            mDeskripLg =itemView.findViewById(R.id.deskripLg);
        }
    }

    public LanguageAdapter(Context context, ArrayList<LanguageItem> languageList) {
        mLanguageList = languageList;
        mContext = context;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_row_item, parent, false);
        LanguageViewHolder lvh = new LanguageViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, final int position) {
        final LanguageItem currentItem = mLanguageList.get(position);

        holder.mImageLg.setImageResource(currentItem.getmImageResource());
        holder.mNameLg.setText(currentItem.getmLanguage());
        holder.mDeskripLg.setText(currentItem.getmDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveData = new Intent(mContext, DetailActivity.class);
                moveData.putExtra("img", mLanguageList.get(position).getmImageResource());
                moveData.putExtra("name", mLanguageList.get(position).getmLanguage());
                moveData.putExtra("deskrip", mLanguageList.get(position).getmDeskripsi());

                mContext.startActivity(moveData);
//                ((Activity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguageList.size();
    }

    public void filterList(ArrayList<LanguageItem> filteredList){
        mLanguageList = filteredList;
        notifyDataSetChanged();
    }
}
