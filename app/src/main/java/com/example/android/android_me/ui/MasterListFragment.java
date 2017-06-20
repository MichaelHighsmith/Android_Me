package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Owner on 6/19/2017.
 */

public class MasterListFragment extends Fragment {

    //Define a new interface onImageClickListener that triggers a callback in the host activity
    onImageClickListener mCallback;

    //onImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface onImageClickListener{
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            mCallback = (onImageClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onImageClickListener");
        }
    }

    public MasterListFragment(){

    }

    //Inflates the gridview of all images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        //Get a reference to the gridview
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        //Create adapter which takes in the context and an ArrayList of all the images to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //Set the adapter on the gridview
        gridView.setAdapter(mAdapter);

        //Set a click listener on the gridview
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                //Trigger the callback method and pass in the position that was clicked
                mCallback.onImageSelected(position);
            }
        });

        //Return the root view
        return rootView;
    }
}
