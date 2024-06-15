package com.springmvc.booklibrary.controller;

import com.springmvc.booklibrary.models.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/login")
public class AdminController {

    @GetMapping
    public String loginView(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "pages/login";
    }

    @PostMapping
    public String connect(Admin admin, HttpSession session, Model model) throws SQLException {
        String id_admin = admin.authenticate();
        if (id_admin != null) {
            session.setAttribute("id", id_admin);
            return "redirect:/book-library";
        }
        return "redirect:/login?error=Authentification failed";
    }

}
