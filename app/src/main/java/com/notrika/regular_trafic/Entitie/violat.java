package com.notrika.regular_trafic.Entitie;

public class violat {
    int id;
    String imgurl;
    String title;
    String discription;

    public violat(int id, String imgurl, String title, String discription) {
        this.id = id;
        this.imgurl = imgurl;
        this.title = title;
        this.discription = discription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
