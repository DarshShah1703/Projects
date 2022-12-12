package com.projectm.cards;

public class DoctorsCard {
    String name;
    int img;

    public DoctorsCard() {

    }

    public DoctorsCard(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
