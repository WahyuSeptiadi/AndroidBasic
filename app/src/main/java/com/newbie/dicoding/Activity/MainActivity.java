package com.newbie.dicoding.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newbie.dicoding.LanguageAdapter;
import com.newbie.dicoding.LanguageItem;
import com.newbie.dicoding.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView btnVisibleSearch, btnCari, btnProfile;
    private ConstraintLayout cari;
    private EditText et_Text;

    private TextView notFound;

    private ArrayList<LanguageItem> mLanguageList;
    ArrayList<LanguageItem> filterList;

    private RecyclerView mRecyclerView;
    private LanguageAdapter mLgAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout sRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVisibleSearch = findViewById(R.id.btnVisible);
        btnCari = findViewById(R.id.btnCari);
        btnProfile = findViewById(R.id.btnProfile);
        cari = findViewById(R.id.searching);

        notFound = findViewById(R.id.not_found);
        et_Text = findViewById(R.id.editTextSearch);

        sRefresh= findViewById(R.id.swapRefresh);

        createExampleList();
        buildRecyclerView();

        sRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLanguageList.clear();
                        notFound.setVisibility(View.GONE);

                        sRefresh.setRefreshing(false);
                        mRecyclerView.setAdapter(mLgAdapter);
                        mLgAdapter.notifyDataSetChanged();

                        createExampleList();
                        buildRecyclerView();
                    }
                }, 1000);
            }
        });

        btnVisibleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cari.setVisibility(View.VISIBLE);
                btnCari.setVisibility(View.VISIBLE);
                btnVisibleSearch.setVisibility(View.INVISIBLE);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cari.setVisibility(View.GONE);
                btnCari.setVisibility(View.GONE);
                btnVisibleSearch.setVisibility(View.VISIBLE);
                notFound.setVisibility(View.GONE);

                if (TextUtils.isEmpty(et_Text.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter Keyword !", Toast.LENGTH_SHORT).show();
                }else{
                    filter(et_Text.getText().toString());
                }
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                // autohide after search keyword
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });
    }

    private void filter(String text){
        filterList = new ArrayList<>();

        for (LanguageItem item : mLanguageList){
            if (item.getmLanguage().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        boolean cek = filterList.isEmpty();

        if (cek == true){
            notFound.setVisibility(View.VISIBLE);
        }

        mLgAdapter.filterList(filterList);
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mLgAdapter = new LanguageAdapter(this, mLanguageList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mLgAdapter);
    }

    private void createExampleList() {
        mLanguageList = new ArrayList<>();
        mLanguageList.add(new LanguageItem(R.drawable.java, "Java",
                "Bahasa pemrograman yang dapat dijalankan di berbagai komputer termasuk telepon genggam."));
        mLanguageList.add(new LanguageItem(R.drawable.python, "Python",
                "Bahasa pemrograman interpretatif multiguna dengan filosofi perancangan yang berfokus pada tingkat keterbacaan kode."));
        mLanguageList.add(new LanguageItem(R.drawable.mysql, "MySQL",
                "Bahasa pemrograman ini mempunyai beberapa fungsi dan perintah yang dapat dipakai untuk menambahkan, mengubah, dan mengelola berbagai macam tipe data seperti integer, float, string, dan semacamnya."));
        mLanguageList.add(new LanguageItem(R.drawable.ruby, "Ruby",
                "Bahasa pemrograman dinamis berbasis skrip yang berorientasi objek, ditulis dengan bahasa pemrograman C dengan kemampuan dasar seperti Perl dan Python."));
        mLanguageList.add(new LanguageItem(R.drawable.r, "R",
                "Bahasa pemrograman dan perangkat lunak untuk analisis statistika dan grafik, R kini menjadi standar de facto di antara statistikawan untuk pengembangan perangkat lunak statistika."));
        mLanguageList.add(new LanguageItem(R.drawable.csharp, "C#",
                "Bahasa pemrograman modern yang bersifat general-purpose, berorientasi objek, yang dapat digunakan untuk membuat program di atas arsitektur Microsoft .NET Framework."));
        mLanguageList.add(new LanguageItem(R.drawable.html, "HTML",
                "Bahasa markah yang digunakan untuk membuat sebuah halaman web, menampilkan berbagai informasi di dalam sebuah penjelajah web Internet dan pemformatan hiperteks sederhana yang ditulis dalam berkas format ASCII agar dapat menghasilkan tampilan wujud yang terintegrasi."));
        mLanguageList.add(new LanguageItem(R.drawable.xml, "XML",
                "Bahasa markup untuk keperluan umum yang disarankan oleh W3C untuk membuat dokumen markup keperluan pertukaran data antar sistem yang beraneka ragam. XML merupakan kelanjutan dari HTML (HyperText Markup Language) yang merupakan bahasa standar untuk melacak Internet."));
        mLanguageList.add(new LanguageItem(R.drawable.bash, "Bash",
                "Bahasa yang berjalan di atas kernel entah itu Linux ataupun Unix, yang berfungsi sebagai penerjemah antara user dan sistem oprasi (kernel). Bash adalah salah satu dari sekian bahasa scripting sheel yang banyak di gunakan saat ini."));
        mLanguageList.add(new LanguageItem(R.drawable.matlab, "Matlab",
                "Sebuah lingkungan komputasi numerikal dan bahasa pemrograman komputer generasi keempat. Meskipun hanya bernuansa numerik, sebuah kotak kakas (toolbox) yang menggunakan mesin simbolik MuPAD, memungkinkan akses terhadap kemampuan aljabar komputer."));
        mLanguageList.add(new LanguageItem(R.drawable.julia, "Julia",
                "Bahasa pemrograman multi-paradigma yang berarti bisa digunakan untuk pemrograman object-oriented maupun pemrograman fungsional."));
        mLanguageList.add(new LanguageItem(R.drawable.visbas, "Visual Basic",
                "Bahasa pemrograman yang menawarkan Integrated Development Environment (IDE) visual untuk membuat program perangkat lunak berbasis sistem operasi Microsoft Windows dengan menggunakan model pemrograman (COM)."));
    }
}
