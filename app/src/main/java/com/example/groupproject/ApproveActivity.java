package com.example.groupproject;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.content.SharedPreferences;
import java.util.ArrayList;



public class ApproveActivity extends AppCompatActivity {

    private ListView listView;
    private List<BorrowRequest> requestList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private List<String> displayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_list); // This XML will contain just the ListView

        listView = findViewById(R.id.approveListView);

        loadSavedRequests();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);

        // When clicked, open detail view
        listView.setOnItemClickListener((parent, view, position, id) -> {
            BorrowRequest request = requestList.get(position);
            Intent intent = new Intent(ApproveActivity.this, ApproveDetailActivity.class);
            intent.putExtra("request_json", new Gson().toJson(request));
            startActivity(intent);
        });
    }

    private void loadSavedRequests() {
        SharedPreferences prefs = getSharedPreferences("permit_data", MODE_PRIVATE);
        Gson gson = new Gson();
        int count = prefs.getInt("request_count", 0); // How many requests stored

        for (int i = 0; i < count; i++) {
            String json = prefs.getString("request_" + i, null);
            if (json != null) {
                BorrowRequest request = gson.fromJson(json, BorrowRequest.class);
                requestList.add(request);
                displayList.add(request.borrowerName + " - " + request.projectName);
            }
        }
    }
}
