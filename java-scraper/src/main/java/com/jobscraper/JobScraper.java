package com.jobscraper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobScraper {

    public static List<Job> scrapeJobs() throws Exception {

        File file = new File("../test.html");
        Document document = Jsoup.parse(file, "UTF-8");

        List<Job> jobs = new ArrayList<>();
        Elements jobElements = document.select("div.job");

        for (Element jobElement : jobElements) {

            String title = jobElement.select("h2").text();
            String company = jobElement.select("p.company").text();
            String location = jobElement.select("p.location").text();

           jobs.add(new Job(title, company, location, "https://example.com", "Sample job description", "Test HTML"));
        }

        return jobs;
    }

    public static void main(String[] args) throws Exception {

        List<Job> jobs = scrapeJobs();

        for (Job job : jobs) {
            job.printJob();
        }
    }
}