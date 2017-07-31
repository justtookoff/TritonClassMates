package com.android.tritonclassmates;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;

/**
 * Created by Donghwee on 2017-07-08.
 */

public class Post implements Serializable {
    private long dateTime;
    private String title;
    private String content;
    private int like;

    /**
     * This is the constructor of Post class
     * @param dateTime
     * @param title
     * @param content
     */
    public Post(long dateTime, String title, String content){
        this.dateTime = dateTime;
        this.title = title;
        this.content = content;
        this.like = 0;
    }

    public String getDateTimeFormatted(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
                , context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(this.dateTime));
    }

    public int addLiked(){
        this.like++;
        return this.getLike();
    }

    public int minusLike(){
        if(this.like > 0) {
            this.like--;
        }
        return this.getLike();
    }

    //Setters
    public void setDateTime(long dateTime){
        this.dateTime = dateTime;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }


    //Getters
    public long getDateTime(){
        return this.dateTime;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public int getLike() {
        return this.like;
    }

}
