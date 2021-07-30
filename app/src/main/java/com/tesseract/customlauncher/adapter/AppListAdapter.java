package com.tesseract.customlauncher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tesseract.customlauncher.databinding.AppInfoItemBinding;
import com.tesseract.tesseractsdk.model.TessAppInfo;

import java.util.ArrayList;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<TessAppInfo> appList;
    private Context context;

    public AppListAdapter(ArrayList<TessAppInfo> appList, Context context) {
        this.appList = appList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        //inflate app list item layout
        AppInfoItemBinding binding = AppInfoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AppViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AppListAdapter.AppViewHolder holder, int position) {

        // setting data to our views of recycler view.
        TessAppInfo info = appList.get(position);
        holder.ivIcon.setImageDrawable(info.getAppIcon());
        holder.tvAppName.setText(String.format("App Name: %s", info.getAppName()));
        holder.tvVerName.setText(String.format("Version Name: %s", info.getVersionName()));
        holder.tvVerCode.setText(String.format("Version Code: %s", info.getVersionCode()));
        holder.tvPackageName.setText(String.format("Package: %s", info.getPackageName()));
        holder.tvActivityName.setText(String.format("Activity: %s", info.getLaunchActivityName()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(info.getLaunchIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private CardView cardView;
        private ImageView ivIcon;
        private TextView tvAppName, tvVerCode, tvVerName, tvPackageName, tvActivityName;

        public AppViewHolder(@NonNull AppInfoItemBinding binding) {
            super(binding.getRoot());
            ivIcon = binding.ivIcon;
            tvAppName = binding.tvAppName;
            tvVerCode = binding.tvVerCode;
            tvVerName = binding.tvVerName;
            tvPackageName = binding.tvPackageName;
            tvActivityName = binding.tvActivityName;
            cardView = binding.cardView;
            // initializing our views

        }
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<TessAppInfo> filterList) {

        // below line is to add our filtered
        // list in our app array list.
        appList = filterList;

        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
}
