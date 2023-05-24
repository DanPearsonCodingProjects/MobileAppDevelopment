package com.example.theatreapp2;

public class TheatrePricesList {
    //Need to define the things in the list
    //Give it a constructor
    //Then add getters and setters this is it

    String show_name;
    int seated;
    int standing;
    int on_stage;
    int grass;
    int boat_A;
    int boat_B;
    int riverbank;
    int inner_circle;
    int outer_circle;

    TheatrePricesList(String show_name1,int seated1,int standing1,int on_stage1,int grass1,int boat_A1,int boat_B1,int riverbank1,int inner_circle1,int outer_circle1){
        show_name = show_name1;
        seated = seated1;
        standing = standing1;
        on_stage = on_stage1;
        grass = grass1;
        boat_A = boat_A1;
        boat_B = boat_B1;
        riverbank = riverbank1;
        inner_circle = inner_circle1;
        outer_circle = outer_circle1;
    }
    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public int getSeated() {
        return seated;
    }

    public void setSeated(int seated) {
        this.seated = seated;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public int getOn_stage() {
        return on_stage;
    }

    public void setOn_stage(int on_stage) {
        this.on_stage = on_stage;
    }

    public int getGrass() {
        return grass;
    }

    public void setGrass(int grass) {
        this.grass = grass;
    }

    public int getBoat_A() {
        return boat_A;
    }

    public void setBoat_A(int boat_A) {
        this.boat_A = boat_A;
    }

    public int getBoat_B() {
        return boat_B;
    }

    public void setBoat_B(int boat_B) {
        this.boat_B = boat_B;
    }

    public int getRiverbank() {
        return riverbank;
    }

    public void setRiverbank(int riverbank) {
        this.riverbank = riverbank;
    }

    public int getInner_circle() {
        return inner_circle;
    }

    public void setInner_circle(int inner_circle) {
        this.inner_circle = inner_circle;
    }

    public int getOuter_circle() {
        return outer_circle;
    }

    public void setOuter_circle(int outer_circle) {
        this.outer_circle = outer_circle;
    }


}
