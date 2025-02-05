package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    // //For Check Request
    // @PostMapping
    // public ResponseEntity<String> createUser(@RequestBody User user) {
    //     System.out.println("Received User:" + user.getName() + 
    //                     "-" + user.getEmail());
    //     return ResponseEntity.ok("User created Successfully");
    // }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "user-list";
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.createUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String showCraeteForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id,Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form";
    }
    
    
}
