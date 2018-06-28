package com.dscviit.vov;

class Events {


    private String name, description,image,date,venue,cn,cd,ce,reg,postedAt,Category,time,relevance,regbtndesc;

    public Events() {
    }

    public Events(String name, String description,String date,String venue,String image,String cn,String cd,String ce,String reg,String Category,String postedAt,String time,String relevance,String regbtndesc) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.date=date;
        this.venue=venue;
        this.cn=cn;
        this.cd=cd;
        this.ce=ce;
        this.reg=reg;
        this.postedAt=postedAt;
        this.Category=Category;
        this.time=time;
        this.relevance=relevance;
        this.regbtndesc=regbtndesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn=cn;
    }


    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd=cd;
    }


    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce=ce;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg=reg;
    }

    public String getPostedAt(){return postedAt;}

    public void setPostedAt(String postedAt){this.postedAt=postedAt;}

    public String getCategory(){return Category;}

    public void setCategory(String Category){this.Category=Category;}

    public void setTime(String time){this.time=time;}

    public String getTime(){return time;}

    public void setRelevance(String relevance){this.relevance=relevance;}

    public  String getRelevance(){return relevance;}

    public void setRegbtndesc(String regbtndesc){this.regbtndesc=regbtndesc;}

    public  String getRegbtndesc(){return regbtndesc;}

}

