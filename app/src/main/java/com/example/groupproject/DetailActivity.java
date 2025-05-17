package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView detailsText;
    Button btnApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailsText = findViewById(R.id.detailsText);
        BorrowRequest request = (BorrowRequest) getIntent().getSerializableExtra("request");

        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(request.borrowerName).append("\n")
                .append("Dept: ").append(request.department).append("\n")
                .append("Project: ").append(request.projectName).append("\n")
                .append("Date: ").append(request.date1).append("\n")
                .append("Time: ").append(request.time).append("\n")
                .append("Venue: ").append(request.venue).append("\n\nItems:\n");

        for (BorrowRequest.Item item : request.items) {
            sb.append("- ").append(item.qty).append(" ")
                    .append(item.description).append(" | ")
                    .append(item.dateOfTransfer).append(" | ")
                    .append(item.locationFrom).append(" ➜ ")
                    .append(item.locationTo).append("\n");
        }

        detailsText.setText(sb.toString());

        // Launch ApproveActivity when button is clicked
        Button btnApprove = findViewById(R.id.btnApprove);
        btnApprove.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, ApproveActivity.class);
            intent.putExtra("request", request);
            startActivity(intent);
        });
    }
}
