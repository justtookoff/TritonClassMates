package com.android.tritonclassmates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Donghwee on 2017-07-08.
 */

public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context, int resource, List<Post> posts) {
        super(context, resource, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_post, null);
        }

        Post post = getItem(position);

        //If post exists, set the texts on the list
        if(post != null){
            TextView title = (TextView) convertView.findViewById(R.id.list_view_title);
            TextView content = (TextView) convertView.findViewById(R.id.list_view_contentPreview);
            TextView date = (TextView) convertView.findViewById(R.id.list_view_date);

            title.setText(post.getTitle());

            //Check the length of the content
            if(post.getContent().length() > 50) {
                content.setText(post.getContent().substring(0, 50));
            }
            else{
                content.setText(post.getContent());
            }

            date.setText(post.getDateTimeFormatted(getContext()));
        }
        return convertView;
    }
}
