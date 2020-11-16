package com.github.JuiceEye.models;

import com.j256.ormlite.field.DatabaseField;
import java.util.Objects;

public class Post {
    @DatabaseField(generatedId = true)
    int postId;
    @DatabaseField(columnName = "text")
    private String text;
    @DatabaseField(columnName = "isVip")
    private Boolean isVip;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(Boolean vip) {
        isVip = vip;
    }


    public Post() {
    }

    public Post(int postId, String text, boolean isVip) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "com.github.JuiceEye.models.Post{" +
                "postId=" + postId +
                ", text='" + text + '\'' +
                ", isVip=" + isVip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId &&
//                Objects.equals(creator, post.creator) &&
//                Objects.equals(postingDate, post.postingDate) &&
                Objects.equals(isVip, post.isVip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isVip);
    }
}
