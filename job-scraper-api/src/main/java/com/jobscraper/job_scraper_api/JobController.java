package com.jobscraper.job_scraper_api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @GetMapping("/api/jobs")
    public List<Job> getJobs() throws Exception {
        return JobScraper.scrapeJobs();
    }
}