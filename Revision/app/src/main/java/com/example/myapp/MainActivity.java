package com.example.myapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.dao.SinhVienDao;
import com.example.myapp.entity.SinhVien;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnEdit;

    TextView txtSelection;
    Spinner spinnerSinhVien;
    String[] items = {
            "Khoa quản trị kinh doanh",
            "Khoa kế toán kiểm toán",
            "Khoa tài chính ngân hàng",
            "Khoa kinh tế và luật",
            "Khoa công nghệ thông tin",
            "Khoa công nghệ sinh học",
            "Khoa xây dựng và điện",
            "Khoa ngoại ngữ"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.btnAdd = findViewById(R.id.btnAdd);
        this.btnAdd.setOnClickListener(view -> {
            SinhVienDao sinhVienDao = new SinhVienDao(this);
            TextView name = findViewById(R.id.txtName);
            long result = sinhVienDao.insertSinhVien(name.getText().toString());
            if(result == 1) {
                Toast.makeText(getApplicationContext(), "Insert SinhVien failed!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Insert SinhVien success!", Toast.LENGTH_SHORT).show();
            }
        });

        this.btnEdit = findViewById(R.id.btnEdit);
        this.btnEdit.setOnClickListener(view -> {
            SinhVienDao sinhVienDao = new SinhVienDao(this);
            SinhVien firstSinhVien = sinhVienDao.getFirstSinhVien();
            if(firstSinhVien != null) {
                Toast.makeText(getApplicationContext(), "First SinhVien: " + firstSinhVien.getName(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No SinhVien found!", Toast.LENGTH_SHORT).show();
            }
        });


        // display Spinner(dropdown)
        this.spinnerSinhVien = findViewById(R.id.spinnerSV);

        ArrayAdapter<String> sinhVienAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.items);
        sinhVienAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        this.spinnerSinhVien.setAdapter(sinhVienAdapter);


        // display listView (danh sách items)
        ListView listViewSinhVien = findViewById(R.id.listViewSV);
        ArrayAdapter<String> adapterLVSV = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.items);
        listViewSinhVien.setAdapter(adapterLVSV);



    }
}