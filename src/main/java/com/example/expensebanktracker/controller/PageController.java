package com.example.expensebanktracker.controller;

import com.example.expensebanktracker.dto.TransactionDTO;
import com.example.expensebanktracker.entity.User;
import com.example.expensebanktracker.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final TransactionService service;

    @GetMapping("/transactions-page")
    public String view(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("transactions", service.getAll(user));
        return "transactions";
    }

    @PostMapping("/transactions-page")
    public String save(@RequestParam(required = false) Long id,
                       @RequestParam Double amount,
                       @RequestParam String description,
                       @RequestParam String date,
                       HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        TransactionDTO dto = new TransactionDTO();
        dto.setAmount(amount);
        dto.setDescription(description);
        dto.setDate(LocalDate.parse(date));

        if (id != null) {
            service.update(id, dto);
        } else {
            service.create(dto, user);
        }

        return "redirect:/transactions-page";
    }

    @PostMapping("/transactions-page/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/transactions-page";
    }

    @GetMapping("/transactions-page/edit/{id}")
    public String edit(@PathVariable Long id, Model model, HttpSession session) {

        var user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        var transaction = service.getById(id);

        model.addAttribute("transactions", service.getAll(user));
        model.addAttribute("editTransaction", transaction);

        return "transactions";
    }
}
