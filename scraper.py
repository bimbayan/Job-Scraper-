from bs4 import BeautifulSoup

with open("test.html", "r", encoding="utf-8") as file:
    html = file.read()

soup = BeautifulSoup(html, "html.parser")

jobs = soup.find_all("div", class_="job")

print("Number of jobs:", len(jobs))

for job in jobs:

    title = job.find("h2").text
    company = job.find("p", class_="company").text
    location = job.find("p", class_="location").text

    job_data = {
        "title": title,
        "company": company,
        "location": location
    }

    print(job_data)