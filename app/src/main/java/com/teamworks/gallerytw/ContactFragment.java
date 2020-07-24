package com.teamworks.gallerytw;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ContactFragment extends Fragment {

    private TextView cNumber, cEmail, tvUser;
    private FirebaseAuth mAuth;
    private Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        cNumber = (TextView) v.findViewById(R.id.contactnumber);
        cEmail = (TextView) v.findViewById(R.id.contactemail);

        tvUser = (TextView) v.findViewById(R.id.userId);
        logout = (Button) v.findViewById(R.id.btnlgt);

        mAuth = FirebaseAuth.getInstance();

        tvUser.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), SignIn.class));
                getActivity().finish();
            }
        });

        String t1 = "Phone Number : +91 9876543210";
        SpannableString st1 = new SpannableString(t1);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+91 9876543210"));
                startActivity(i);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };

        st1.setSpan(clickableSpan1, 15, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cNumber.setText(st1);
        cNumber.setMovementMethod(LinkMovementMethod.getInstance());

        String t2 = "Email : abc@xyz.com";
        SpannableString st2 = new SpannableString(t2);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[] {"abc@xyz.com"});
                startActivity(Intent.createChooser(i, "Send email.."));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };

        st2.setSpan(clickableSpan2, 8, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cEmail.setText(st2);
        cEmail.setMovementMethod(LinkMovementMethod.getInstance());

        return v;
    }
}