package com.example.midterm2020671055;

public class book {
    String bTitle;
    String people;
    Integer year;

    public book(String bTitle, String people, Integer year) {
        this.bTitle = bTitle;
        this.people = people;
        this.year = year;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
