package database.controllers;


import database.DTO.MathFunctionsDTO;
import database.service.MathFunctionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class MathFunctionsContorellerTest {

    @Mock
    private MathFunctionsService mathFunctionsService;

    @InjectMocks
    private MathFunctionsContoreller mathFunctionsController;

    private MathFunctionsDTO mathFunctionsDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mathFunctionsDTO = new MathFunctionsDTO();
        mathFunctionsDTO.setId(1L);
        mathFunctionsDTO.setFunctionName("Test Function");
    }

    @Test
    void createShouldReturnCreatedMathFunction() {
        when(mathFunctionsService.create(any(MathFunctionsDTO.class))).thenReturn(mathFunctionsDTO);

        ResponseEntity<MathFunctionsDTO> response = mathFunctionsController.create(mathFunctionsDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mathFunctionsDTO, response.getBody());
    }

    @Test
    void readShouldReturnMathFunction() {
        when(mathFunctionsService.read(anyLong())).thenReturn(mathFunctionsDTO);

        ResponseEntity<MathFunctionsDTO> response = mathFunctionsController.read(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mathFunctionsDTO, response.getBody());
    }

    @Test
    void updateShouldReturnUpdatedMathFunction() {
        when(mathFunctionsService.update(any(MathFunctionsDTO.class))).thenReturn(mathFunctionsDTO);

        ResponseEntity<MathFunctionsDTO> response = mathFunctionsController.update(1L, mathFunctionsDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mathFunctionsDTO, response.getBody());
    }

    @Test
    void deleteShouldReturnOkWhenFunctionExists() {
        when(mathFunctionsService.getById(anyLong())).thenReturn(mathFunctionsDTO);

        ResponseEntity<Void> response = mathFunctionsController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(mathFunctionsService, times(1)).delete(mathFunctionsDTO);
    }

    @Test
    void deleteShouldReturnNotFoundWhenFunctionDoesNotExist() {
        when(mathFunctionsService.getById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = mathFunctionsController.delete(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void findByNameShouldReturnFunctionsList() {
        when(mathFunctionsService.findsByName(anyString())).thenReturn(Collections.singletonList(mathFunctionsDTO));

        ResponseEntity<List<MathFunctionsDTO>> response = mathFunctionsController.findDyName("Test Function");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
}