package com.hfad.bitsandpizzas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class    PastaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pastaRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_pasta,container,false);


String[] pastaNames = new String[Pasta.pastas.length];
for(int i = 0; i<pastaNames.length;i++){
    pastaNames[i]=Pasta.pastas[i].getName();
}
int[] pastaImages = new int[Pasta.pastas.length];
for(int i = 0; i< pastaImages.length;i++){
    pastaImages[i]=Pasta.pastas[i].getImageResourceId();
}
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pastaNames, pastaImages);
        pastaRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        pastaRecycler.setLayoutManager(layoutManager);
        return pastaRecycler;
    }
}
