package web.controllers;

import database.repositories.MathFunctionsRepository;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tabulated-operations")
public class TabulatedOperationsController {

    @Autowired
    public MathFunctionsRepository mathFunctionsRepository;

    @GetMapping
    public String showForm(Model model, HttpSession session,
                           @RequestParam(required = false) boolean showError,
                           @RequestParam(required = false) String errorMessage,
                           @RequestParam(required = false) String redirectTarget) {
        System.out.println(showError);
        if(showError) {
            model.addAttribute("showError", showError);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("redirectTarget", redirectTarget);
        }

        if(session.getAttribute("operand1Func") == null) {
            model.addAttribute("operand1Func",null);
        }
        else{
            TabulatedFunction func = (TabulatedFunction) session.getAttribute("operand1Func");

            model.addAttribute("operand1Func",session.getAttribute("operand1Func"));
            model.addAttribute("function1",func);
            model.addAttribute("count1", func.getCount());
        }

        if(session.getAttribute("operand2Func") == null) {
            model.addAttribute("operand2Func",null);
        }
        else{
            TabulatedFunction func = (TabulatedFunction) session.getAttribute("operand2Func");
            model.addAttribute("operand2Func",session.getAttribute("operand2Func"));
            model.addAttribute("function2",func);
            model.addAttribute("count2", func.getCount());
        }

        if(session.getAttribute("resultFunc") == null) {
            model.addAttribute("resultFunc",null);
        }
        else{
            TabulatedFunction result = (TabulatedFunction) session.getAttribute("resultFunc");
            model.addAttribute("resultFunc",session.getAttribute("resultFunc"));
            model.addAttribute("result",result);
            model.addAttribute("count3", result.getCount());
        }

        model.addAttribute("functions",mathFunctionsRepository.findAll());

        return "tabulated-operations";
    }

    @PostMapping("/doOperation")
    public String doOperation(@RequestParam String operation, Model model, HttpSession session) {

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService((TabulatedFunctionFactory) session.getAttribute("FACTORY_KEY"));
        TabulatedFunction result = null;
        if(session.getAttribute("operand1Func") != null && session.getAttribute("operand2Func") != null) {
            TabulatedFunction func1 = (TabulatedFunction) session.getAttribute("operand1Func");
            TabulatedFunction func2 = (TabulatedFunction) session.getAttribute("operand2Func");

            switch (operation) {
                case "add":
                    result = service.add(func1, func2);
                    break;
                case "subtract":
                    result = service.subtract(func1, func2);
                    break;
                case "multiply":
                    result = service.multiply(func1, func2);
                    break;
                case "divide":
                    result = service.devide(func1, func2);
                    break;
            }
        }

        session.setAttribute("resultFunc", result);
        return "redirect:/tabulated-operations";
    }
}
