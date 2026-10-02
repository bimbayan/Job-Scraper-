package com.jobscraper.job_scraper_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ScraperService {

    public List<Job> scrapeAllSources() throws Exception {

        List<Job> allJobs = new ArrayList<>();

        List<JobSource> sources = List.of(
                new RemoteOkSource()
        );

        for (JobSource source : sources) {
            allJobs.addAll(source.scrapeJobs());
        }

        return allJobs;
    }
}