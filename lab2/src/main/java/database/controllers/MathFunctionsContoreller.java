package database.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import database.DTO.MathFunctionsDTO;
import database.service.MathFunctionsService;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class MathFunctionsContoreller {

    private final MathFunctionsService mathFunctionsService;

    @Autowired
    public MathFunctionsContoreller(MathFunctionsService mathFunctionsService) {
        this.mathFunctionsService = mathFunctionsService;
    }
//- Метод принимает данные о математической функции в формате JSON
// в теле запроса и преобразует их в объект MathFunctionsDTO.
//   - Сервис mathFunctionsService вызывается для создания новой функции.
//   - Возвращается HTTP-ответ с кодом 200 (OK) и созданным объектом.
    @PostMapping
    public ResponseEntity<MathFunctionsDTO> create(@RequestBody MathFunctionsDTO dto_obj){
        MathFunctionsDTO response = mathFunctionsService.create(dto_obj);
        return ResponseEntity.ok(response);
    }
// - Метод принимает идентификатор функции из URL и получает соответствующий объект через сервис.
//   - Возвращается HTTP-ответ с кодом 200 и найденным объектом.
    @GetMapping("/{id}")
    public ResponseEntity<MathFunctionsDTO> read(@PathVariable long id){
        MathFunctionsDTO response = mathFunctionsService.read(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MathFunctionsDTO> update(@PathVariable Long id,@RequestBody MathFunctionsDTO dto_obj){
        dto_obj.setId(id);
        MathFunctionsDTO response = mathFunctionsService.update(dto_obj);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(mathFunctionsService.getById(id) != null) {
            mathFunctionsService.delete(mathFunctionsService.getById(id));
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public  ResponseEntity<List<MathFunctionsDTO>> findDyName(@RequestParam String name){
        List<MathFunctionsDTO> functions = mathFunctionsService.findsByName(name);
        if(functions.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(functions);
    }//- Если функции найдены, возвращается список с кодом 200 (OK).
   //- Если список пуст, возвращается код 204 (No Content).
}
