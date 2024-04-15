package com.example.appstudent.Model;

public class QuyCheModel {
    private String tieude,link;
    private boolean expanble = false;

    public QuyCheModel(String tieude, String link, boolean expanble) {
        this.tieude = tieude;
        this.link = link;
        this.expanble = expanble;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isExpanble() {
        return expanble;
    }

    public void setExpanble(boolean expanble) {
        this.expanble = expanble;
    }
}
