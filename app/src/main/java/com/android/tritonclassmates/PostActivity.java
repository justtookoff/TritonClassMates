package com.android.tritonclassmates;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Donghwee on 2017-06-30.
 */

public class PostActivity extends AppCompatActivity {
    private Toolbar postToolbar;
    private EditText postTitle;
    private TextView postDate;
    private EditText postContent;

    private String fileName;
    private Post loadPost = null;
    private long postCreationTime;

    private static final int IMAGE_GALLERY_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postToolbar = (Toolbar) findViewById(R.id.post_toolbar);
        postTitle = (EditText) findViewById(R.id.post_title);
        postDate = (TextView) findViewById(R.id.post_date);
        postContent = (EditText) findViewById(R.id.post_content);

        //Setting the toolbar
        setSupportActionBar(postToolbar);

        //Check if they have content already
        //Get filename first
        //이거는 포스트를 로딩하는거임..(loading the post if already existed)
        fileName = getIntent().getStringExtra(Utilities.EXTRAS_NOTE_FILENAME);
        if(fileName != null && !fileName.isEmpty() && fileName.endsWith(Utilities.FILE_EXTENSION)) {
            loadPost = Utilities.getNoteByFileName(getApplicationContext(), fileName);

            if(loadPost != null){
                postTitle.setText(loadPost.getTitle());
                postContent.setText(loadPost.getContent());
                postCreationTime = loadPost.getDateTime();
            }
            else{
                postCreationTime = System.currentTimeMillis();
            }
        }

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

    /**
     * Reacting the option Selected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.action_camera:
                //Use google api to take a picture within this app
                this.camera();
                break;
            case R.id.action_picture:
                //Go to the gallery to get the pictures
                this.photoPicker();
                break;
            case R.id.action_save:
                //We don't save all the time
                //Save when the user put any a word in it
                if(postTitle.getText().toString().length() > 0 ||
                        postContent.getText().toString().length() > 0) {
                    this.savePost();
                    finish();
                } else{
                    //Else we discard the post
                    Toast.makeText(this, "Your post has been discarded", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * When the back button get pressed
     */
    @Override
    public void onBackPressed(){
        //If there is any word
        if(postTitle.getText().toString().length() > 0 ||
                postContent.getText().toString().length() > 0) {
            AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(this)
                    .setMessage("Save or discard the post?")
                    .setPositiveButton("Save", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            savePost();
                            finish();
                        }
                    })
                    .setNegativeButton("Discard", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                        }
                    })
                    .setNeutralButton("Cancel", null);

            dialogConfirm.show();
        }
    }
    /**
     * This method is to save the post
     */
    private void savePost(){
        //Make new post and make a new post
        Post post = new Post(System.currentTimeMillis(),
                postTitle.getText().toString(), postContent.getText().toString());

        //To show the toast message
        if(Utilities.savePost(this, post)){
            Toast.makeText(this, "Your post is saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Your post is not saved", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This is a photo picker method
     * This method is to show the album of the phone
     */
    private void photoPicker(){
        //Check the permission
        int permissionCheck = ContextCompat.checkSelfPermission(PostActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        //This is to show the album of the phone by using intent and the code of the gallery
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    /**
     * 카메라 액티비티 보여주는 메소드임!
     */
    private void camera(){

    }


    /**
     * This is to show the result of the activity
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        //Show the Gallery
        if(requestCode == IMAGE_GALLERY_REQUEST) {
             if(intent != null) {
                 //This part is for getting picture from the gallery and paste on the editText
                 //It seems I need to use spannable code,but I can't figure it out right now
                 //앨범에서 사진 골라서 에딧텍스트에 붙이는!
                 /*
                 Uri imageUri = intent.getData();
                 String[] filePathColumn = {MediaStore.Images.Media.DATA};

                 Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                 cursor.moveToFirst();

                 int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                 String img_Decodable_Str = cursor.getString(columnIndex);
                 cursor.close();

                 this.postContent.getText().setSpan(new ImageSpan(BitmapFactory.decodeFile(img_Decodable_Str)), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
             }
             else{

             }
        }
    }

}
