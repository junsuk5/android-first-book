package com.example.firebasechatexam;

/**
 * Created by junsuk on 2017. 8. 24..
 */

public class ChatMessage {

    private String id;          // DB에 저장할 ID
    private String text;        // 메시지
    private String name;        // 이름
    private String photoUrl;    // 프로필 사진 경로
    private String imageUrl;    // 첨부 이미지 경로

    public ChatMessage() {
    }

    public ChatMessage(String text, String name, String photoUrl, String imageUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

