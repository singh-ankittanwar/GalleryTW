package com.teamworks.gallerytw;

public class apiItem {

    private int id;
    private String title;
    private String thumbnailUrl;

    public apiItem(int id, String title, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
