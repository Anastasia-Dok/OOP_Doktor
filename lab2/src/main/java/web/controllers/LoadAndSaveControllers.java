package web.controllers;

import database.entity.MathFunctionsEntity;
import database.entity.PointEntity;
import database.repositories.MathFunctionsRepository;
import exceptions.LoadFunctionExecption;
import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import database.DTO.MathFunctionsDTO;
import database.DTO.PointDTO;
import database.service.MathFunctionsService;
import database.service.PointService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/memory")
public class LoadAndSaveControllers {

    @Autowired
    public MathFunctionsRepository mathFunctionsRepository;

    private final MathFunctionsService mathFunctionsService;
    private final PointService pointService;

    @Autowired
    public LoadAndSaveControllers(MathFunctionsService mathFunctionsService, PointService pointService) {
        this.mathFunctionsService = mathFunctionsService;
        this.pointService = pointService;
    }

    @PostMapping("/load")
    public String load(@RequestParam("target") String target, @RequestParam("id") Long id, Model model, HttpSession session) {
        System.out.println("Loading " + target);

        System.out.println(target);

        MathFunctionsEntity loadFunc = mathFunctionsRepository.findById(id).orElse(null);

        List<PointEntity> list = loadFunc.getPoints();
        if(list.size() >= 2){
            double[] x = new double[list.size()];
            double[] y = new double[list.size()];

            int i = 0;
            for(PointEntity point : list) {
                System.out.println(point.getId());
                x[i] = point.getX();
                System.out.println(point.getX());
                y[i] = point.getY();
                System.out.println(point.getY());
                i++;
            }

            TabulatedFunction result = new ArrayTabulatedFunction(x,y);
            session.setAttribute(target+"Func", result);
            return "redirect:/tabulated-operations";
        }else{
            throw new LoadFunctionExecption("У функции должно быть >2 точек");
        }
    }

    @PostMapping("/save")
    public String save(@RequestParam("target") String saveTarget, @RequestParam("funcName") String funcName, Model model, HttpSession session) {

        TabulatedFunction func = (TabulatedFunction) session.getAttribute(saveTarget+"Func");
        if(func == null) {
            throw new IllegalArgumentException("Function is empty");
        }

        MathFunctionsDTO dto = new MathFunctionsDTO();
        dto.setFunctionName(funcName);
        dto.setxTo(func.rightBound());
        dto.setxFrom(func.leftBound());
        dto.setCount(func.getCount());

        Long idResult = mathFunctionsService.create(dto).getId();

        for(int i = 0; i < func.getCount(); i++) {
            PointDTO point = new PointDTO();
            point.setFunction(idResult);
            point.setX(func.getX(i));
            point.setY(func.getY(i));

            pointService.create(point);
        }

        return "redirect:/tabulated-operations";
    }

}
