package com.example.chefswipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Cards> {
    
    Context context;

    public arrayAdapter(Context context, int resourceId, List<Cards> items) {
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cards card_item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipeName);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipeImage);

        recipeName.setText(card_item.getRecipeName());
        recipeImage.setImageResource(R.mipmap.ic_launcher);

        return convertView;

    }

}
