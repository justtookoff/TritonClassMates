package com.android.tritonclassmates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by Donghwee on 2017-06-30.
 */

public class PostActivity extends AppCompatActivity {
    private Toolbar postToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postToolbar = (Toolbar) findViewById(R.id.post_toolbar);
        setSupportActionBar(postToolbar);
    }

    /**
     * This is for action menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post_menu, menu);
        return true;
    }
}
