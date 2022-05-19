package com.example.doggie.controllers;

import com.example.doggie.domain.Activity;
import com.example.doggie.domain.Pet;
import com.example.doggie.domain.Poroda;
import com.example.doggie.domain.User;
import com.example.doggie.repos.ActivityRepo;
import com.example.doggie.repos.PetRepo;
import com.example.doggie.repos.PorodaRepo;
import com.example.doggie.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    String nickname;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private PorodaRepo porodaRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String profile() {
        return "redirect:/profile";
    }

    @GetMapping("{pet}")
    public String petEditForm(@PathVariable Pet pet, Model model) {
        Object userNickname = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(userNickname);

        User objectUser = userRepo.findByUsername(nickname);

        model.addAttribute("nickname", nickname);
        model.addAttribute("add", false);
        model.addAttribute("pet", pet);
        model.addAttribute("user", objectUser);

        List<Poroda> porodas = porodaRepo.findAll();
        List<Activity> activities = activityRepo.findAll();

        model.addAttribute("porodas", porodas);
        model.addAttribute("activities", activities);

        return "petEdit";
    }

    @GetMapping("/post")
    public String petAddForm(Model model) {
        Object userNickname = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(userNickname);

        Pet pet = new Pet();

        User objectUser = userRepo.findByUsername(nickname);

        model.addAttribute("nickname", nickname);
        model.addAttribute("add", true);
        model.addAttribute("pet", pet);

        List<Poroda> porodas = porodaRepo.findAll();
        List<Activity> activities = activityRepo.findAll();

        model.addAttribute("porodas", porodas);
        model.addAttribute("activities", activities);

        return "petEdit";
    }

    @GetMapping("/delete/{pet}")
    public String petDelete(@PathVariable Pet pet) {
        try {
            petRepo.delete(pet);

            return "redirect:/profile";
        }
        catch (Exception e) {
            System.out.println("Упс...ошибка. " + e.getMessage());
            return "redirect:/profile";
        }
    }

    @PostMapping("/post")
    public String petAdd(
            @ModelAttribute("pet") Pet pet
    ) {
        Object userNickname = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(userNickname);

        User objectUser = userRepo.findByUsername(nickname);

        pet.setUser(objectUser);
        petRepo.save(pet);

        return "redirect:/profile";
    }

    @PostMapping
    public String petSave(
            @RequestParam String name,
            @RequestParam Integer age,
            @RequestParam Integer rost,
            @RequestParam Integer ves,
            @RequestParam Poroda poroda,
            @RequestParam Activity activity,
            @RequestParam("petId") Pet pet
    ) {
        Object userNickname = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(userNickname);

        User objectUser = userRepo.findByUsername(nickname);
        pet.setName(name);
        pet.setAge(age);
        pet.setRost(rost);
        pet.setVes(ves);
        pet.setPoroda(poroda);
        pet.setActivity(activity);
        pet.setUser(objectUser);
        petRepo.save(pet);

        return "redirect:/profile";
    }

    public String getUsername(Object user) {
        if (user instanceof UserDetails) {
            nickname = ((UserDetails)user).getUsername();
        } else {
            nickname = user.toString();
        }

        if (nickname.equals("anonymousUser")) {
            nickname = null;
        }

        return nickname;
    }

}
