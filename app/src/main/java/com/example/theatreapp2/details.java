package com.example.theatreapp2;

public class details {

    //Takes in what you want to be in the list
    String title;
    String description;
    String date;

    String location;

    Boolean wheelchairAccess;
    Boolean epilepsy;
    int images;
    int time;


    public Boolean getWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(Boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

    public Boolean getEpilepsy() {
        return epilepsy;
    }

    public void setEpilepsy(Boolean epilepsy) {
        this.epilepsy = epilepsy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //Give it a constructor so that when you make a new details class you assign them these
    //Variables which will then be given a variable in the current system
    details(String title1, String desc1,String d,int img,int tim, boolean wheelchair,boolean ep,String loc){
        title = title1;
        description = desc1;
        date = d;
        images = img;
        time = tim;
        wheelchairAccess = wheelchair;
        epilepsy = ep;
        location = loc;

    }


    //Just getters and setters, self explanatory
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
