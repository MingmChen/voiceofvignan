package com.dscviit.vov;

public class Clubs {

    private String name, description,image,activities,team,poc;

    public Clubs() {
    }

    public Clubs(String name, String description,String activities,String team,String poc,String image) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.activities=activities;
        this.team=team;
        this.poc=poc;
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
    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team= team;
    }

    public String getPoc() {
        return poc;
    }

    public void setPOC(String poc) {
        this.poc =poc;
    }
}
