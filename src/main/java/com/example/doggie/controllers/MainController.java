package com.example.doggie.controllers;

import com.example.doggie.domain.Pet;
import com.example.doggie.domain.User;
import com.example.doggie.repos.PetRepo;
import com.example.doggie.repos.UserRepo;
import com.example.doggie.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class MainController {
    String nickname;
    HashMap<Pet, Object> lowWeightLightMealData;
    HashMap<Pet, Object> normalWeightLightMealData;
    HashMap<Pet, Object> overWeightLightMealData;
    HashMap<Pet, Object> lowWeightMiddleMealData;
    HashMap<Pet, Object> normalWeightMiddleMealData;
    HashMap<Pet, Object> overWeightMiddleMealData;
    HashMap<Pet, Object> lowWeightHardMealData;
    HashMap<Pet, Object> normalWeightHardMealData;
    HashMap<Pet, Object> overWeightHardMealData;
    HashMap<Pet, Object> lowActiveDomTrainData;
    HashMap<Pet, Object> middleActiveDomTrainData;
    HashMap<Pet, Object> highActiveDomTrainData;
    HashMap<Pet, Object> lowActiveProTrainData;
    HashMap<Pet, Object> middleActiveProTrainData;
    HashMap<Pet, Object> highActiveProTrainData;
    HashMap<Pet, Object> lowActiveSportTrainData;
    HashMap<Pet, Object> middleActiveSportTrainData;
    HashMap<Pet, Object> highActiveSportTrainData;

    @Autowired
    private MailSender mailSender;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PetRepo petRepo;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        if (nickname != null) {
            User userObject = userRepo.findByUsername(nickname);
            model.put("name", userObject.getName());
        }

        model.put("nickname", nickname);

        return "index";
    }

    @GetMapping("/about")
    public String about(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        model.put("nickname", nickname);

        return "about";
    }

    @GetMapping("/mealrec")
    public String mealRecommend(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        model.put("nickname", nickname);

        return "mealRecommend";
    }

    @GetMapping("/vetmap")
    public String vetMap(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        model.put("nickname", nickname);

        return "vetMap";
    }

    @GetMapping("/contact")
    public String contact(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        if (nickname != null) {
            User userObject = userRepo.findByUsername(nickname);
            model.put("name", userObject.getName());
        }

        model.put("nickname", nickname);

        return "contact";
    }

    @PostMapping("/contact")
    public String contactPost(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String message,
                              Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        String successMessage = "Ваше сообщение успешно отправлено!";

        mailSender.contact(name, email, message);
        model.put("successMessage", successMessage);
        model.put("nickname", nickname);

        return "contact";
    }

    @GetMapping("/profile")
    public String profile(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        User userObject = userRepo.findByUsername(nickname);

        model.put("name", userObject.getName());
        model.put("surname", userObject.getSurname());
        model.put("email", userObject.getEmail());
        model.put("pets", userObject.getPets());
        model.put("nickname", nickname);

        return "profile";
    }

    @GetMapping("/meal")
    public String meal(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        User userObject = userRepo.findByUsername(nickname);

        model.put("pets", userObject.getPets());
        model.put("nickname", nickname);

        return "meal";
    }

    @PostMapping("/meal")
    public String mealCreate(
            @RequestParam("mealPriority") String mealPriority,
            @RequestParam("petId") Pet pet,
            Map<String, Object> model) throws Exception {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        model.put("pet", pet.getName());
        model.put("mealPriority", mealPriority);
        model.put("nickname", nickname);

        if (Objects.equals(mealPriority, "Готовый корм")) {
            if (pet.getAge() <= 1) {
                if (pet.getRost() <= 30 && pet.getVes() <= 15) {
                    model.put("data", lowWeightLightMealData);
                    model.put("image", "kormLight");
                    return "kormLight";
                }
                else if (pet.getRost() <= 60 && pet.getRost() > 30 && pet.getVes() > 15 && pet.getVes() <= 30) {
                    model.put("data", normalWeightLightMealData);
                    model.put("image", "kormLight");
                    return "kormLight";
                }
                else if (pet.getRost() > 60 && pet.getVes() > 30) {
                    model.put("data", overWeightLightMealData);
                    model.put("image", "kormLight");
                    return "kormLight";
                }
                else {
                    model.put("image", "kormLight");
                    return "kormLight";
                }
            }
            else if (pet.getAge() > 1 && pet.getAge() <= 5) {
                if (pet.getRost() <= 42 && pet.getVes() <= 23) {
                    model.put("data", lowWeightMiddleMealData);
                    model.put("image", "kormMiddle");
                    return "kormMiddle";
                }
                else if (pet.getRost() <= 80 && pet.getRost() > 44 && pet.getVes() > 25 && pet.getVes() <= 35) {
                    model.put("data", normalWeightMiddleMealData);
                    model.put("image", "kormMiddle");
                    return "kormMiddle";
                }
                else if (pet.getRost() > 80 && pet.getVes() > 35) {
                    model.put("data", overWeightMiddleMealData);
                    model.put("image", "kormMiddle");
                    return "kormMiddle";
                }
                else {
                    model.put("image", "kormMiddle");
                    return "kormMiddle";
                }
            }
            else if (pet.getAge() > 5) {
                if (pet.getRost() <= 55 && pet.getVes() <= 30) {
                    model.put("data", lowWeightHardMealData);
                    model.put("image", "kormHard");
                    return "kormHard";
                }
                else if (pet.getRost() <= 80 && pet.getRost() > 25 && pet.getVes() > 10 && pet.getVes() <= 50) {
                    model.put("data", normalWeightHardMealData);
                    model.put("image", "kormHard");
                    return "kormHard";
                }
                else if (pet.getRost() > 80 && pet.getVes() > 50) {
                    model.put("data", overWeightHardMealData);
                    model.put("image", "kormHard");
                    return "kormHard";
                }
                else {
                    model.put("image", "kormHard");
                    return "kormHard";
                }
            }
            else {
                return "error";
            }
        }
        else if (Objects.equals(mealPriority, "Натуральная еда")) {
            if (pet.getAge() <= 1) {
                if (pet.getRost() <= 30 && pet.getVes() <= 15) {
                    model.put("data", lowWeightLightMealData);
                    model.put("image", "natLight");
                    return "natLight";
                }
                else if (pet.getRost() <= 60 && pet.getRost() > 30 && pet.getVes() > 15 && pet.getVes() <= 30) {
                    model.put("data", normalWeightLightMealData);
                    model.put("image", "natLight");
                    return "natLight";
                }
                else if (pet.getRost() > 60 && pet.getVes() > 30) {
                    model.put("data", overWeightLightMealData);
                    model.put("image", "natLight");
                    return "natLight";
                }
                else {
                    model.put("image", "natLight");
                    return "natLight";
                }
            }
            else if (pet.getAge() > 1 && pet.getAge() <= 5) {
                if (pet.getRost() <= 42 && pet.getVes() <= 23) {
                    model.put("data", lowWeightMiddleMealData);
                    model.put("image", "natMiddle");
                    return "natMiddle";
                }
                else if (pet.getRost() <= 80 && pet.getRost() > 44 && pet.getVes() > 25 && pet.getVes() <= 35) {
                    model.put("data", normalWeightMiddleMealData);
                    model.put("image", "natMiddle");
                    return "natMiddle";
                }
                else if (pet.getRost() > 80 && pet.getVes() > 35) {
                    model.put("data", overWeightMiddleMealData);
                    model.put("image", "natMiddle");
                    return "natMiddle";
                }
                else {
                    model.put("image", "natMiddle");
                    return "natMiddle";
                }
            }
            else if (pet.getAge() > 5) {
                if (pet.getRost() <= 55 && pet.getVes() <= 30) {
                    model.put("data", lowWeightHardMealData);
                    model.put("image", "natHard");
                    return "natHard";
                }
                else if (pet.getRost() <= 80 && pet.getRost() > 25 && pet.getVes() > 10 && pet.getVes() <= 50) {
                    model.put("data", normalWeightHardMealData);
                    model.put("image", "natHard");
                    return "natHard";
                }
                else if (pet.getRost() > 80 && pet.getVes() > 50) {
                    model.put("data", overWeightHardMealData);
                    model.put("image", "natHard");
                    return "natHard";
                }
                else {
                    model.put("image", "natHard");
                    return "natHard";
                }
            }
            else {
                return "error";
            }
        }
        else {
            return "error";
        }
    }

    @GetMapping("/training")
    public String training(Map<String, Object> model) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        User userObject = userRepo.findByUsername(nickname);

        model.put("pets", userObject.getPets());
        model.put("nickname", nickname);

        return "training";
    }

    @PostMapping("/training")
    public String trainingCreate(
            @RequestParam("trainPriority") String trainPriority,
            @RequestParam("petId") Pet pet,
            Map<String, Object> model) throws Exception {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        nickname = getUsername(user);

        model.put("pet", pet.getName());
        model.put("nickname", nickname);
        model.put("trainPriority", trainPriority);

        if (Objects.equals(trainPriority, "Домашние тренировки")) {
            if (Objects.equals(pet.getActivity().getTitle(), "Низкая")) {
                model.put("data", lowActiveDomTrainData);
                model.put("image", "trainingDom");
                return "trainingDom";
            }
            else if (Objects.equals(pet.getActivity().getTitle(), "Средняя")) {
                model.put("data", middleActiveDomTrainData);
                model.put("image", "trainingDom");
                return "trainingDom";
            }
            else if (Objects.equals(pet.getActivity().getTitle(), "Высокая")) {
                model.put("data", highActiveDomTrainData);
                model.put("image", "trainingDom");
                return "trainingDom";
            }
            else {
                model.put("image", "trainingDom");
                return "trainingDom";
            }
        }
        else if (Objects.equals(trainPriority, "Профессиональная дрессировка")) {
            if (Objects.equals(pet.getActivity().getTitle(), "Низкая")) {
                model.put("data", lowActiveProTrainData);
                model.put("image", "trainingPro");
                return "trainingPro";
            }
            else if (Objects.equals(pet.getActivity().getTitle(), "Средняя")) {
                model.put("data", middleActiveProTrainData);
                model.put("image", "trainingPro");
                return "trainingPro";
            }
            else if (Objects.equals(pet.getActivity().getTitle(), "Высокая")) {
                model.put("data", highActiveProTrainData);
                model.put("image", "trainingPro");
                return "trainingPro";
            }
            else {
                model.put("image", "trainingPro");
                return "trainingPro";
            }
        }
        else if (Objects.equals(trainPriority, "Спортивная дрессировка")) {
            if (Objects.equals(pet.getActivity().getTitle(), "Низкая")) {
                model.put("data", lowActiveProTrainData);
                model.put("image", "trainingSport");
                return "trainingSport";
            }
            else if (Objects.equals(pet.getActivity().getTitle(), "Средняя")) {
                model.put("data", middleActiveProTrainData);
                model.put("image", "trainingSport");
                return "trainingSport";
            }
            else if (Objects.equals(pet.getActivity().getTitle(), "Высокая")) {
                model.put("data", highActiveProTrainData);
                model.put("image", "trainingSport");
                return "trainingSport";
            }
            else {
                model.put("image", "trainingSport");
                return "trainingSport";
            }
        }
        else {
            return "error";
        }
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
