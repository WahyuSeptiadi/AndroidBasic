package com.newbie.dicoding.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.newbie.dicoding.R;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView foto;
    private ImageView btnBack, btnSave, editVisible;
    private EditText username, email, editUsername, editEmail;

    private ConstraintLayout icon;
    private LinearLayout show, edit;

    public static final String SHARED_PREF = "sharedPref";
    public static final String saveUsernameHere = "username";
    public static final String saveEmailHere = "email";

    String usernameHere;
    String emailHere;

    // upload IMAGE
    private Bitmap bitmap;
    private static int RESULT_LOAD_IMAGE = 1;
    public static final String  key = "nameKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        editVisible = findViewById(R.id.editVisible);

        foto = findViewById(R.id.foto);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        // duplicate
//        edit = findViewById(R.id.editLinear);
//        icon = findViewById(R.id.iconku);
//        editUsername = findViewById(R.id.editUsername);
//        editEmail = findViewById(R.id.editEmail);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(backHome);
                finish();
            }
        });

        editVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave.setVisibility(View.VISIBLE);
                editVisible.setVisibility(View.INVISIBLE);

                username.setEnabled(true);
                email.setEnabled(true);

                Toast.makeText(ProfileActivity.this, "Click the text above to edit text!", Toast.LENGTH_SHORT).show();

                email.setTypeface(null, Typeface.NORMAL);
                username.setTypeface(null, Typeface.NORMAL);

                // BELUM BISA
                username.setCursorVisible(true);
                username.setFocusableInTouchMode(true);
                username.isInEditMode();
                Drawable originalDrawable = username.getBackground();
                username.setBackground(originalDrawable);

//                icon.setVisibility(View.VISIBLE);
//                edit.setVisibility(View.VISIBLE);
//                show.setVisibility(View.INVISIBLE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave.setVisibility(View.INVISIBLE);
                editVisible.setVisibility(View.VISIBLE);

                username.setEnabled(false);
                email.setEnabled(false);

                username.setTypeface(null, Typeface.BOLD);
                email.setTypeface(null, Typeface.ITALIC);

//                icon.setVisibility(View.INVISIBLE);
//                edit.setVisibility(View.INVISIBLE);
//                show.setVisibility(View.VISIBLE);

                saveData();
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                openImage();
            }
        });

        loadData();
        updateViews();
    }

    // USERNAME & EMAIL
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(saveUsernameHere, username.getText().toString());
        editor.putString(saveEmailHere, email.getText().toString());

        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        usernameHere = sharedPreferences.getString(saveUsernameHere, "Wahyu Septiadi");
        emailHere = sharedPreferences.getString(saveEmailHere, "wahyusptd@gmail.com");
    }

    public void updateViews(){
        username.setText(usernameHere);
        email.setText(emailHere);
    }

    // IMAGE UPLOAD
    private void openImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    private void confirmOpenImage(){
        Intent i = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }


}
