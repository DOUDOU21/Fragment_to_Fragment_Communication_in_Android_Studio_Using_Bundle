package com.ndoudou.usingbundle;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ndoudou.usingbundle.model.User;

public class InfoFragment extends Fragment {
    private TextView nomTextView, prenomTextView, emailTextView;
    private Button shareButton;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_info, container, false);
        init();
        return view;
    }

    public void init(){
        nomTextView = view.findViewById(R.id.text_view_nom);
        prenomTextView = view.findViewById(R.id.text_view_prenom);
        emailTextView = view.findViewById(R.id.text_view_email);
        shareButton = view.findViewById(R.id.button_share);
        displayData();

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partagerInfos();
            }
        });
    }

    private void displayData(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            User user = (User) bundle.getSerializable("user");
            nomTextView.setText(user.getNom());
            prenomTextView.setText(user.getPrenom());
            emailTextView.setText(user.getEmail());
        }
    }

    private void partagerInfos() {
        Intent partagerIntent = new Intent(Intent.ACTION_SEND);
        partagerIntent.setType("text/plain");
        partagerIntent.putExtra(Intent.EXTRA_TEXT, "Informations à partager");
        startActivity(Intent.createChooser(partagerIntent, "Partager via"));
    }
}