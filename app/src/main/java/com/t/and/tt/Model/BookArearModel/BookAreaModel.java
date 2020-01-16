package com.t.and.tt.Model.BookArearModel;

import java.util.ArrayList;

public class BookAreaModel {

    String title;
    ArrayList time = new ArrayList();

    public BookAreaModel() {
    }

    public BookAreaModel(String title , ArrayList time) {

        this.title = title;
        this.time = time;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTime(ArrayList time) {
        this.time = time;
    }

    public ArrayList getTime() {
        return time;
    }
}



