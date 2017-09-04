package com.example.anfal.newsapp;

/**
 * Created by Anfal on 8/26/17.
 */

public class News {

    private String title;
    private String webPublicationDate;
    private String section;
    private String webUrl;

    public News(String title, String webPublicationDate, String section, String webUrl) {
        this.title = title;
        this.webPublicationDate = webPublicationDate;
        this.section = section;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getSection() {
        return section;
    }

    public String getWebUrl() { return webUrl; }
}
