package com.example.springboot_09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired  // maps dependency to JobRepository (pipeline to table defined in Job.java)
    JobRepository jobRepository;

    @RequestMapping("/") // route for endpoint / (default route)
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "list"; // launch View template list.html
    }

    @GetMapping("/add")  // route for endpoint /add
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobform"; // launch View template jobform.html
    }

    @PostMapping("/process")  // route for endpoint /process
    public String processForm(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "jobform"; // launch View template jobform.html
        }
        jobRepository.save(job);
        return "redirect:/"; // return to route for endpoint /
    }

}
