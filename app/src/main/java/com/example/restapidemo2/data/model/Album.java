package com.example.restapidemo2.data.model;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class Album {

    /**
     * userId : 1
     * id : 1
     * title : quidem molestiae enim
     */

    private int userId;
    private int id;
    private String title;
    private String thumbnailUrl;

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
