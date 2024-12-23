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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MathFunctionsContorellerTest {

    @InjectMocks
    private MathFunctionsContoreller mathFunctionsController;

    @Mock
    private MathFunctionsService mathFunctionsService;

    private MathFunctionsDTO mathFunctionsDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mathFunctionsDTO = new MathFunctionsDTO();
        mathFunctionsDTO.setId(1L);
        mathFunctionsDTO.setFunctionName("Test Function");
    }

    @Test
    public void testCreate() {
        when(mathFunctionsService.create(any(MathFunctionsDTO.class))).thenReturn(mathFunctionsDTO);

        ResponseEntity<MathFunctionsDTO> response = mathFunctionsController.create(mathFunctionsDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mathFunctionsDTO, response.getBody());
        verify(mathFunctionsService, times(1)).create(any(MathFunctionsDTO.class));
    }

    @Test
    public void testRead() {
        when(mathFunctionsService.read(1L)).thenReturn(mathFunctionsDTO);

        ResponseEntity<MathFunctionsDTO> response = mathFunctionsController.read(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mathFunctionsDTO, response.getBody());
        verify(mathFunctionsService, times(1)).read(1L);
    }

    @Test
    public void testUpdate() {
        when(mathFunctionsService.update(any(MathFunctionsDTO.class))).thenReturn(mathFunctionsDTO);

        ResponseEntity<MathFunctionsDTO> response = mathFunctionsController.update(1L, mathFunctionsDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mathFunctionsDTO, response.getBody());
        verify(mathFunctionsService, times(1)).update(any(MathFunctionsDTO.class));
    }

    @Test
    public void testDelete() {
        when(mathFunctionsService.getById(1L)).thenReturn(mathFunctionsDTO);

        ResponseEntity<Void> response = mathFunctionsController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(mathFunctionsService, times(1)).delete(mathFunctionsDTO);
    }

    @Test
    public void testDeleteNotFound() {
        when(mathFunctionsService.getById(1L)).thenReturn(null);

        ResponseEntity<Void> response = mathFunctionsController.delete(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(mathFunctionsService, never()).delete(any());
    }

    @Test
    public void testFindByName() {
        List<MathFunctionsDTO> functions = Collections.singletonList(mathFunctionsDTO);
        when(mathFunctionsService.findsByName("Test Function")).thenReturn(functions);

        ResponseEntity<List<MathFunctionsDTO>> response = mathFunctionsController.findByName("Test Function");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(functions, response.getBody());
        verify(mathFunctionsService, times(1)).findsByName("Test Function");
    }

    @Test
    public void testFindByNameNoContent() {
        when(mathFunctionsService.findsByName("Unknown Function")).thenReturn(Collections.emptyList());

        ResponseEntity<List<MathFunctionsDTO>> response = mathFunctionsController.findByName("Unknown Function");

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(mathFunctionsService, times(1)).findsByName("Unknown Function");
    }
}
