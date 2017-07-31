package com.android.tritonclassmates;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        //Floating action button for writing
        FloatingActionButton writePost = (FloatingActionButton) findViewById(R.id.write_post);
        writePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PostActivity.class);
                startActivityForResult(intent, 0);
            }
        });

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
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Restart the activity
     */
    @Override
    protected  void onResume(){
        super.onResume();

        //load saved notes into the listview
        //first, reset the listview
        freeBoard_ListView.setAdapter(null);
        ArrayList<Post> posts = Utilities.getAllSavedPosts(getApplicationContext());

        //sort notes from new to old
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post lhs, Post rhs) {
                if(lhs.getDateTime() > rhs.getDateTime()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        if(posts != null && posts.size() > 0) { //check if we have any notes!
            final PostAdapter na = new PostAdapter(this, R.layout.view_post, posts);
            freeBoard_ListView.setAdapter(na);

            //set click listener for items in the list, by clicking each item the note should be loaded into NoteActivity
            //FreeBoard에 있는 게시물을 클릭했을때! 뷰모드로 켜져여함
            freeBoard_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((Post) freeBoard_ListView.getItemAtPosition(position)).getDateTime()
                            + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(), PostActivity.class);
                    viewNoteIntent.putExtra(Utilities.EXTRAS_NOTE_FILENAME, fileName);
                    startActivity(viewNoteIntent);
                }
            });
        }
        else { //remind user that we have no notes!
            Toast.makeText(getApplicationContext(), "you have no saved notes!\ncreate some new notes :)"
                    , Toast.LENGTH_SHORT).show();
        }
    }

}
