package com.tesseract.customlauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tesseract.customlauncher.adapter.AppListAdapter;
import com.tesseract.customlauncher.databinding.ActivityMainBinding;
import com.tesseract.tesseractsdk.TessSDK;
import com.tesseract.tesseractsdk.model.TessAppInfo;
import com.tesseract.tesseractsdk.receiver.AppUninstallReceiver;
import com.tesseract.tesseractsdk.receiver.NewAppInstallReceiver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //view binding
    private ActivityMainBinding binding;

    private AppListAdapter adapter;
    private ArrayList<TessAppInfo> appsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        TessSDK.registerInstallBroadcast();
        TessSDK.registerUninstallBroadcast();
        bindRecyclerView();
    }


    private void bindRecyclerView() {
        //initialize list
        appsArrayList = TessSDK.getAllPackages();

        // initializing our adapter class.
        adapter = new AppListAdapter(appsArrayList, MainActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.appsRecyclerView.setHasFixedSize(true);
        binding.appsRecyclerView.setLayoutManager(manager);
        binding.appsRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<TessAppInfo> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (TessAppInfo item : appsArrayList) {

            // checking if the entered string matched with any item of our recycler view.
            if (item.getAppName().toLowerCase().contains(text.toLowerCase())) {

                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TessSDK.unregisterInstallReceiver();
        TessSDK.unregisterUninstallReceiver();
    }
}