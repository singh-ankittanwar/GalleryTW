package com.teamworks.gallerytw;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

public class ImageFragment extends Fragment {

    private static final int q = 123;
    private ImageView pickImg;
    private Button getImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);

        pickImg = v.findViewById(R.id.putImg);
        getImg = v.findViewById(R.id.toGallery);

        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Pick an Image.."), q);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == q && resultCode == RESULT_OK && data != null){

            Uri imageData = data.getData();
            pickImg.setImageURI(imageData);
            pickImg.setVisibility(View.VISIBLE);
            getImg.setVisibility(View.INVISIBLE);
        }
    }
}