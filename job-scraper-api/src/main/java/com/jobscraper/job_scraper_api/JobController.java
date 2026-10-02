package com.jobscraper.job_scraper_api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobRepository jobRepository;
    private final ScraperService scraperService;

    public JobController(
            JobRepository jobRepository,
            ScraperService scraperService) {

        this.jobRepository = jobRepository;
        this.scraperService = scraperService;
    }

    @GetMapping("/api/jobs")
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @PostMapping("/api/jobs/scrape")
    public List<Job> scrapeAndSaveJobs() throws Exception {

        List<Job> jobs = scraperService.scrapeAllSources();

        for (Job job : jobs) {

            if (!jobRepository.existsByTitleAndCompanyAndLocation(
                    job.getTitle(),
                    job.getCompany(),
                    job.getLocation())) {

                jobRepository.save(job);
            }
        }

        return jobs;
    }
}