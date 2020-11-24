package com.duolingo.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.duolingo.app.R;
import com.duolingo.app.models.Category;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<Category> mData;
    private LayoutInflater mInflater;
    private Context context;

    public CategoriesAdapter(List<Category> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.item_category, null);
        return new CategoriesAdapter.ViewHolder(view);

    }

    public void setItems(List<Category> items) {
        mData = items;

    }

    public void onBindViewHolder(final CategoriesAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public int getItemCount(){
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPhoto;
        TextView tvTitle, tvLevel;

        ViewHolder(View itemView){
            super(itemView);
            //ivPhoto = itemView.findViewById(R.id.ivIcon);
            tvTitle = itemView.findViewById(R.id.rTvTitle);
            tvLevel = itemView.findViewById(R.id.rTvLevel);
        }

        void bindData(final Category item){
            //ivPhoto.setImageBitmap(item.getImage());
            tvTitle.setText(item.getTitle());
            tvLevel.setText(item.getLevel());
        }

    }

}
