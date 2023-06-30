package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@Controller
@RequestMapping("/currency23")
public class CurrencyController {
    @GetMapping
    public String getIndex(){
        return "currency";
    }

    @PostMapping("/convert")
    public String convertUSD(@RequestParam long usd, Model model) {
        long vnd = convertToVND(usd);
        model.addAttribute("usd", formatCurrency(usd, "$"));
        model.addAttribute("usd123", usd);
        model.addAttribute("vnd", formatCurrency(vnd, "VND"));
        return "currency";
    }

    @PostMapping("/convert1")
    public String convertVND(@RequestParam long vnd, Model model) {
        long usd = convertToUSD(vnd);
        model.addAttribute("usd", formatCurrency(usd, "$"));
        model.addAttribute("vnd123", vnd);
        model.addAttribute("vnd", formatCurrency(vnd, "VND"));
        return "currency";
    }

    private long convertToVND(long usd) {
        return usd * 22000;
    }

    private long convertToUSD(long vnd) {
        return vnd / 22000;
    }

    private String formatCurrency(long amount, String currency) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(amount) + " " + currency;
    }
}
