package com.dscviit.vov;

class Announcements {


    private String imageurl, description, postedDate, postedBy, category,relevance;

    public Announcements() {
    }

    public Announcements(String imageurl, String description, String postedDate, String postedBy, String category,String relevance) {
        this.imageurl = imageurl;
        this.description = description;
        this.postedDate = postedDate;
        this.postedBy = postedBy;
        this.category = category;
        this.relevance=relevance;

    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRelevance(String relevance){this.relevance=relevance;}

    public  String getRelevance(){return relevance;}



}
