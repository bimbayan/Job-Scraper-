package com.jobscraper.job_scraper_api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemoteOkSource implements JobSource {

    private static final String API_URL = "https://remoteok.com/api";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Job> scrapeJobs() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("User-Agent", "JobScraper/1.0")
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Remote OK API returned status: " + response.statusCode()
            );
        }

        JsonNode root = objectMapper.readTree(response.body());

        List<Job> jobs = new ArrayList<>();

        for (JsonNode item : root) {

            if (!item.hasNonNull("position")) {
                continue;
            }

            String title = item.path("position").asText("");
            String company = item.path("company").asText("");
            String location = item.path("location").asText("Remote");
            String descriptionHtml = item.path("description").asText("");
            String description = Jsoup.parse(descriptionHtml).text();
            String url = item.path("url").asText(
                    item.path("apply_url").asText("")
            );

            if (title.isBlank() || company.isBlank() || url.isBlank()) {
                continue;
            }

            jobs.add(new Job(
                    title,
                    company,
                    location,
                    url,
                    description,
                    "Remote OK"
            ));
        }

        return jobs;
    }
}