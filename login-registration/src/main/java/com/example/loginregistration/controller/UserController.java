package com.example.loginregistration.controller;

import com.example.loginregistration.details.CurrentUserDetail;
import com.example.loginregistration.enums.RoleType;
import com.example.loginregistration.model.User;
import com.example.loginregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/sign-up")
    public String createNewAccount(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        User tempUser = user;
        tempUser.setRole(RoleType.ROLE_USER);
        tempUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        tempUser.setCreatedDate(new Date());
        userService.save(tempUser);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public ModelAndView sign(@RequestParam(value = "error", required = false) String error) {
        System.out.println("get map");
        ModelAndView view = new ModelAndView();
        if (error != null)
            view.setViewName("error");
        else view.setViewName("sign");
        return view;

    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "welcome";
    }

}
