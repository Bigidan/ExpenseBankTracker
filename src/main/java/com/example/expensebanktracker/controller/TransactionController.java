package com.example.expensebanktracker.controller;

import com.example.expensebanktracker.dto.TransactionDTO;
import org.springframework.web.bind.annotation.*;

import com.example.expensebanktracker.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionDTO> getAll(jakarta.servlet.http.HttpSession session) {

        var user = (com.example.expensebanktracker.entity.User)
                session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("Not logged in");
        }

        return service.getAll(user);
    }

    @PostMapping
    public TransactionDTO create(@RequestBody TransactionDTO dto,
                                 jakarta.servlet.http.HttpSession session) {

        var user = (com.example.expensebanktracker.entity.User)
                session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("Not logged in");
        }

        return service.create(dto, user);
    }

    @PutMapping("/{id}")
    public TransactionDTO update(@PathVariable Long id,
                                 @RequestBody TransactionDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/filter")
    public List<TransactionDTO> filter(
            @RequestParam String from,
            @RequestParam String to) {

        return service.filterByDate(
                LocalDate.parse(from),
                LocalDate.parse(to)
        );
    }
}
