package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 6/19/2017.
 */

public class BodyPartFragment extends Fragment {

    private static final String TAG = "BodyPartFragment";

    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    private List<Integer> mImageIds;
    private int mListIndex;

    public BodyPartFragment(){

    }

    //onCreateView is called immediately after onCreate to create the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Load the saved state
        if(savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        //Set the image resource to display
        if(mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            //Set an onclick listener for the image view
            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //Increment the list to the next image in the list
                    if(mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    } else {
                        //Reset the image back to the first one (for when it reaches the end of the list
                        mListIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else{
            Log.v(TAG, "This fragment has a null list of image id's");
        }

        //Return the rootview
        return rootView;
    }

    public void setImageIds(List<Integer> imageIds){
        mImageIds = imageIds;
    }

    public void setListIndex(int index){
        mListIndex = index;
    }

    //Override this method so that the images dont go back to the first one when the device is rotated
    @Override
    public void onSaveInstanceState(Bundle currentState){
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
