package com.example.recyclerviewexam;

/**
 * Created by junsuk on 2017. 8. 8..
 */

public class CardItem {
    private String title;
    private String contents;

    public CardItem(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CardItem{");
        sb.append("title='").append(title).append('\'');
        sb.append(", contents='").append(contents).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
