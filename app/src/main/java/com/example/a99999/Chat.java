package com.example.a99999;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sht.homework.R;

public class Chat extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chat, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(new Multimedia_Farm());

        Button imageView1 = (Button) getActivity().findViewById(R.id.button_1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(new Multimedia_Farm());
            }
        });
        Button imageView2 = (Button) getActivity().findViewById(R.id.button_2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(new Multimedia_Video());
            }
        });
        Button imageView3 = (Button) getActivity().findViewById(R.id.button_3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(new JingDong());
            }
        });

        Button imageView4 = (Button) getActivity().findViewById(R.id.button_4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replaceFragment(new TaoBao());
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.chat_fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
