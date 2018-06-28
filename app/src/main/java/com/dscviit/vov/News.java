package com.dscviit.vov;

public class News {


    private String headline, content,image,postedBy,Category,postedAt;

    public News() {
    }

    public News(String headline, String content, String image,String postedBy,String Category,String postedAt) {
        this.headline = headline;
        this.content = content;
        this.image = image;
        this.postedBy=postedBy;
        this.Category=Category;
        this.postedAt=postedAt;

    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPostedBy(){return postedBy;}

    public void setPostedBy(String postedBy){this.postedBy=postedBy;}

    public String getPostedAt(){return postedAt;}

    public void setPostedAt(String postedAt){this.postedAt=postedAt;}

    public String getCategory(){return Category;}

    public void setCategory(String Category){this.Category=Category;}

}
