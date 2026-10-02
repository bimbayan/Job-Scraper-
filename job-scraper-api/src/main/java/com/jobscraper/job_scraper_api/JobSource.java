package com.jobscraper.job_scraper_api;

import java.util.List;

public interface JobSource {
    List<Job> scrapeJobs() throws Exception;
}