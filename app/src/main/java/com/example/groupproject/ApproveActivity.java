package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ApproveActivity extends AppCompatActivity {

    private TextView detailsText;

    private TextView textDate, textDepartment, textRequestingName, textProjectName, textDateTime, textVenue;
    private TextView textQty, textDescription, textTransferDate, textFrom, textTo, textReturnDate, textRemarks;
    private TextView textApprovedBy;

    Button btnApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_form);



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

        // Load and show the data
        loadRequestData();


    }

    private void loadRequestData() {
        BorrowRequest request = (BorrowRequest) getIntent().getSerializableExtra("request");
        if (request == null) {
            Toast.makeText(this, "No request data received", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set general info
        textDate.setText(request.date1);
        textDepartment.setText(request.department);
        textRequestingName.setText(request.borrowerName);
        textProjectName.setText(request.projectName);
        textDateTime.setText(request.time);
        textVenue.setText(request.venue);

        // Set first item (you can loop if needed)
        if (request.items != null && !request.items.isEmpty()) {
            BorrowRequest.Item item = request.items.get(0); // assuming 1 item for now
            textQty.setText(String.valueOf(item.qty));
            textDescription.setText(item.description);
            textTransferDate.setText(item.dateOfTransfer);
            textFrom.setText(item.locationFrom);
            textTo.setText(item.locationTo);
        }

        // Optional defaults
        textReturnDate.setText("N/A");
        textRemarks.setText("None");
        textApprovedBy.setText("Pending");

        // Launch ApproveActivity when button is clicked
        Button btnApprove = findViewById(R.id.btnApprove);
        btnApprove.setOnClickListener(v -> {
            Intent intent = new Intent(ApproveActivity.this, ApproveActivity.class);
            intent.putExtra("request", request);
            startActivity(intent);
        });

    }


}
