package com.android.tritonclassmates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    //Get the toolbar
    private Toolbar homeToolbar;
    //Preview of the boardList
    private ListView boardListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call the Schedule


        //Call the previewOfListView for the boards
        boardListView = (ListView) findViewById(R.id.board_list_view);
        homeToolbar = (Toolbar) findViewById(R.id.homeToolbar);
        homeToolbar.setTitle("TritonClassMates");
        //setSupportActionBar(homeToolbar);

        populatePreListView();
        registerClickCallBack();
    }

    private void populatePreListView(){
        //Create lists of items
        String[] items = { "FreeBoard", "AnonymousBoard", "InfoBoard", "JobBoard", "BuyAndSell", "ClubBoard", "TimeTable"};

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,  //Context for the activity
                R.layout.preview_items, //layout ot use
                items); //items to be displayed

        //Configure the list view
        boardListView.setAdapter(adapter);
    }

    private void registerClickCallBack(){

        boardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){
                Intent intent;

                switch(position) {
                    //FreeBoardActivity
                    case 0 :
                        intent = new Intent(viewClicked.getContext(), FreeBoardActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    //AnonymousBoard
                    case 1 :
                        //intent = new Intent(viewClicked.getContext(), AnonymousBoard.class);
                        //startActivityForResult(intent, 0);
                        break;
                    //InfoBoard
                    case 2 :
                        //intent = new Intent(viewClicked.getContext(), InfoBoard.class);
                        //startActivityForResult(intent, 0);
                        break;
                    //JobBoard
                    case 3 :
                        //intent = new Intent(viewClicked.getContext(), JobBoard.class);
                        //startActivityForResult(intent, 0);
                        break;
                    //BuyAndSell
                    case 4 :
                        //intent = new Intent(viewClicked.getContext(), BuyAndSellBoard.class);
                        //startActivityForResult(intent, 0);
                        break;
                    //ClubBoard
                    case 5 :
                        //intent = new Intent(viewClicked.getContext(), ClubBoard.class);
                        //startActivityForResult(intent, 0);
                        break;
                    case 6:
                        intent = new Intent(viewClicked.getContext(), TimeTableActivity.class);
                        startActivityForResult(intent, 0);
                    default:
                        break;
                }
            }

        });
    }
}
