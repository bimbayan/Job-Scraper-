package com.jobscraper;

public class Job {

    private String title;
    private String company;
    private String location;

    public Job(String title, String company, String location) {
        this.title = title;
        this.company = company;
        this.location = location;
    }

    public void printJob() {
        System.out.println("Title: " + title);
        System.out.println("Company: " + company);
        System.out.println("Location: " + location);
    }
}