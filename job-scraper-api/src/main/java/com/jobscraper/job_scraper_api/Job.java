package com.jobscraper.job_scraper_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String url;
    private String description;
    private String source;

    public Job() {
    }

    public Job(String title, String company, String location,
               String url, String description, String source) {

        this.title = title;
        this.company = company;
        this.location = location;
        this.url = url;
        this.description = description;
        this.source = source;
    }

    public void printJob() {
        System.out.println("Title: " + title);
        System.out.println("Company: " + company);
        System.out.println("Location: " + location);
        System.out.println("URL: " + url);
        System.out.println("Source: " + source);
        System.out.println();
    }

    public String getTitle() {
    return title;
}

public String getCompany() {
    return company;
}

public String getLocation() {
    return location;
}

public String getUrl() {
    return url;
}

public String getDescription() {
    return description;
}

public String getSource() {
    return source;
}
}