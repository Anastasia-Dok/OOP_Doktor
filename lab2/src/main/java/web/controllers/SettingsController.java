package web.controllers;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class SettingsController {


    @PostMapping("/settings")
    public String updateSettings(@RequestParam("factoryType") String factoryType, HttpSession session) {

        TabulatedFunctionFactory factory = null;

        if ("array".equals(factoryType)) {
            factory = new ArrayTabulatedFunctionFactory();
        } else if ("linkedlist".equals(factoryType)) {
            factory = new LinkedListTabulatedFunctionFactory();
        }

        session.setAttribute("FACTORY_KEY", factory);

        return "home";
    }
}
