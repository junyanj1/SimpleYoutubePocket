package com.ics45j.junyanj1.simpleyoutubepocket;

public class LinkClip {
    private String uid;
    private String youtuberName;
    private String linkToVideo;
    private String addingTime;

    public LinkClip(){
        youtuberName = "NA";
        linkToVideo = "NA";
        addingTime = "NA";
    }

    public LinkClip(String name, String link, String time, String uid){
        this.youtuberName = name;
        this.linkToVideo = link;
        this.addingTime = time;
        this.uid = uid;
    }

    public String getYoutuberName(){return youtuberName;}

    public String getLinkToVideo(){return linkToVideo;}

    public String getAddingTime(){return addingTime;}

    public String getUid(){return uid;}

    public String toString(){
        String result = "Youtuber name: " + youtuberName + "\n"
                        + "Video Link: " + linkToVideo + "\n"
                        + "Archived at: " + addingTime;
        return result;
    }
}
