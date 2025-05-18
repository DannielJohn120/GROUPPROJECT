package com.example.groupproject;

import android.widget.TextView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;

public class ApproveDetailActivity extends AppCompatActivity {

    private TextView textDate, textDepartment, textRequestingName, textProjectName, textDateTime, textVenue;
    private TextView textQty, textDescription, textTransferDate, textFrom, textTo, textReturnDate, textRemarks;
    private TextView textApprovedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_form); // This is the detailed layout

        // Find all views
        textDate = findViewById(R.id.textViewDate);
        textDepartment = findViewById(R.id.textViewDepartment);
        textRequestingName = findViewById(R.id.textViewRequestingName);
        textProjectName = findViewById(R.id.textViewProjectName);
        textDateTime = findViewById(R.id.textViewDateTime);
        textVenue = findViewById(R.id.textViewVenue);
        textQty = findViewById(R.id.textViewQty);
        textDescription = findViewById(R.id.textViewDescription);
        textTransferDate = findViewById(R.id.textViewTransferDate);
        textFrom = findViewById(R.id.textViewFrom);
        textTo = findViewById(R.id.textViewTo);
        textReturnDate = findViewById(R.id.textViewReturnDate);
        textRemarks = findViewById(R.id.textViewRemarks);
        textApprovedBy = findViewById(R.id.textViewApprovedBy);

        // Get data
        String json = getIntent().getStringExtra("request_json");
        BorrowRequest request = new Gson().fromJson(json, BorrowRequest.class);
        if (request != null) {
            textDate.setText(request.date1);
            textDepartment.setText(request.department);
            textRequestingName.setText(request.borrowerName);
            textProjectName.setText(request.projectName);
            textDateTime.setText(request.time);
            textVenue.setText(request.venue);

            if (request.items != null && !request.items.isEmpty()) {
                BorrowRequest.Item item = request.items.get(0);
                textQty.setText(String.valueOf(item.qty));
                textDescription.setText(item.description);
                textTransferDate.setText(item.dateOfTransfer);
                textFrom.setText(item.locationFrom);
                textTo.setText(item.locationTo);
            }

            textReturnDate.setText("N/A");
            textRemarks.setText("None");
            textApprovedBy.setText("");
        }
    }
}
