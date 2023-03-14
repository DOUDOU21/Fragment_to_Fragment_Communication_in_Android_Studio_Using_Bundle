package com.ndoudou.usingbundle;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ndoudou.usingbundle.model.User;

public class FormFragment extends Fragment {

    private EditText mNomEditText, mPrenomEditText, mEmailEditText;
    private Button mValiderButton;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_form, container, false);
        init();
        return view;
    }

    public void init(){
        mNomEditText = view.findViewById(R.id.edit_text_nom);
        mPrenomEditText = view.findViewById(R.id.edit_text_prenom);
        mEmailEditText = view.findViewById(R.id.edit_text_email);
        mValiderButton = view.findViewById(R.id.button_valider);

        mValiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(mNomEditText.getText().toString(),
                        mPrenomEditText.getText().toString(),
                        mEmailEditText.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                sendData(new InfoFragment(), bundle);

            }
        });
    }

    private void sendData(Fragment fragment, Bundle data){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragment.setArguments(data);
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}