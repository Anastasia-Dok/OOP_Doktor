package database.controllers;

import database.DTO.PointDTO;
import database.service.PointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PointControllerTest {

    @Mock
    private PointService functionPointService;

    @InjectMocks
    private PointController functionPointController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        PointDTO functionPointDTO = new PointDTO();
        functionPointDTO.setX(2.0);
        functionPointDTO.setY(4.0);

        when(functionPointService.create(any(PointDTO.class))).thenReturn(functionPointDTO);

        ResponseEntity<PointDTO> response = functionPointController.create(functionPointDTO);
        assertNotNull(response.getBody());
        assertEquals(2.0, response.getBody().getX());
        verify(functionPointService, times(1)).create(any(PointDTO.class));
    }

    @Test
    public void testRead() {
        Long id = 1L;
        PointDTO functionPointDTO = new PointDTO();
        functionPointDTO.setId(id);

        when(functionPointService.read(id)).thenReturn(functionPointDTO);

        ResponseEntity<PointDTO> response = functionPointController.read(id);
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        verify(functionPointService, times(1)).read(id);
    }

//    @Test
//    public void testDelete() {
//        Long id = 1L;
//        doNothing().when(functionPointService).delete(1L);
//
//        ResponseEntity<Void> response = functionPointController.delete(id);
//        assertEquals(200, response.getStatusCodeValue());
//        verify(functionPointService, times(1)).delete(1L);
//    }

//    @Test
//    public void testGetByFunction() {
//        Long functionId = 2L;
//        List<PointDTO> points = Collections.singletonList(new PointDTO());
//
//        when(functionPointService.getFunction(functionId)).thenReturn(points);
//
//        ResponseEntity<List<PointDTO>> response = functionPointController.getFunction(functionId);
//        assertNotNull(response.getBody());
//        assertFalse(response.getBody().isEmpty());
//        verify(functionPointService, times(1)).getFunction(functionId);
//    }
}

