package com.android.tritonclassmates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Created by Donghwee on 2017-06-29.
 */

public class FreeBoardActivity extends AppCompatActivity {
    //Get the toolbar
    private Toolbar freeBoard_Toolbar;
    //FreeBoardActivity ListView
    private ListView freeBoard_ListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard);

        //Getting the list view and tool bar
        freeBoard_ListView = (ListView) findViewById(R.id.freeBoard_list_view);
        freeBoard_Toolbar = (Toolbar) findViewById(R.id.freeBoardToolbar);

        //Setting the toolbar name and action bar
        freeBoard_Toolbar.setTitle("FreeBoard");
        setSupportActionBar(freeBoard_Toolbar);

    }

    /**
     * This is for action menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.freeboard_menu, menu);
        return true;
    }

    /**
     * Reacting the option Selected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;

        switch(item.getItemId()) {
            case R.id.action_refresh:
                break;
            case R.id.action_settings:
                break;
            case R.id.write_post:
                intent = new Intent(this, PostActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
