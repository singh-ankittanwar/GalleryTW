package com.teamworks.gallerytw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.b_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFL, new ContactFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selected = null;

                    switch (item.getItemId()){
                        case R.id.contact:
                            selected = new ContactFragment();
                            break;

                        case R.id.image:
                            selected = new ImageFragment();
                            break;

                        case R.id.viewImage:
                            selected = new ViewImageFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFL, selected).commit();

                    return true;
                }
            };
}