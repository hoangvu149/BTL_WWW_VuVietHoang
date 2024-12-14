package vn.edu.iuh.fit.frontend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.iuh.fit.backend.Models.Account;
import vn.edu.iuh.fit.backend.Services.AccountService;
import vn.edu.iuh.fit.backend.Services.SkillService;

@Controller
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SkillService skillService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("skills", skillService.getAllSkills());
        return "register";
    }

    @PostMapping("/register")
    public String register(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.save(account);

        return "redirect:/login";
    }
}