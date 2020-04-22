package com.newbie.dicoding.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newbie.dicoding.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;
import static android.text.Layout.JUSTIFICATION_MODE_NONE;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgLg, btnBack;
    private TextView nameLg, deskLg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgLg = findViewById(R.id.imgLanguage);
        nameLg = findViewById(R.id.nameLanguage);
        deskLg = findViewById(R.id.deskripLanguage);

        int gambar = getIntent().getExtras().getInt("img");
        String nama = getIntent().getExtras().getString("name");
        String deskripsi = getIntent().getExtras().getString("deskrip");

        imgLg.setImageResource(gambar);
        nameLg.setText(nama);
        deskLg.setText(deskripsi);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(backHome);
                finish();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            deskLg.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }else{
            deskLg.setGravity(Gravity.CENTER);
        }

    }
}
