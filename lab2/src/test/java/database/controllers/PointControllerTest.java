package database.controllers;

import database.DTO.PointDTO;
import database.service.PointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PointControllerTest {

    @Mock
    private PointService pointService;

    @InjectMocks
    private PointController pointController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        PointDTO inputDto = new PointDTO();
        inputDto.setFunction(100L);

        PointDTO returnedDto = new PointDTO();
        returnedDto.setId(1L);
        returnedDto.setFunction(100L);

        when(pointService.create(inputDto)).thenReturn(returnedDto);

        ResponseEntity<PointDTO> response = pointController.create(inputDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(returnedDto, response.getBody());
        verify(pointService, times(1)).create(inputDto);
    }

    @Test
    void testRead() {
        long id = 1L;
        PointDTO returnedDto = new PointDTO();
        returnedDto.setId(id);
        returnedDto.setFunction(100L);

        when(pointService.read(id)).thenReturn(returnedDto);

        ResponseEntity<PointDTO> response = pointController.read(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(returnedDto, response.getBody());
        verify(pointService, times(1)).read(id);
    }

    @Test
    void testUpdate() {
        long id = 1L;
        PointDTO inputDto = new PointDTO();
        inputDto.setFunction(100L);

        PointDTO returnedDto = new PointDTO();
        returnedDto.setId(id);
        returnedDto.setFunction(100L);

        when(pointService.update(inputDto)).thenReturn(returnedDto);

        ResponseEntity<PointDTO> response = pointController.update(id, inputDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(returnedDto, response.getBody());
        verify(pointService, times(1)).update(inputDto);
    }

    @Test
    void testDelete_Success() {
        long id = 1L;
        PointDTO existingDto = new PointDTO();
        existingDto.setId(id);

        when(pointService.getById(id)).thenReturn(existingDto);
        doNothing().when(pointService).delete(existingDto);

        ResponseEntity<Void> response = pointController.delete(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(pointService, times(1)).getById(id);
        verify(pointService, times(1)).delete(existingDto);
    }


    @Test
    void testDelete_NotFound() {
        long id = 1L;

        when(pointService.getById(id)).thenReturn(null);

        ResponseEntity<Void> response = pointController.delete(id);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(pointService, times(1)).getById(id);
        verify(pointService, never()).delete(any(PointDTO.class));
    }

    @Test
    void testFindPointsByFunction_Success() {
        long functionId = 1L;
        PointDTO dto1 = new PointDTO();
        dto1.setId(1L);
        dto1.setFunction(100L);

        PointDTO dto2 = new PointDTO();
        dto2.setId(2L);
        dto2.setFunction(100L);

        List<PointDTO> points = Arrays.asList(dto1, dto2);

        when(pointService.findByFunction(functionId)).thenReturn(points);

        ResponseEntity<List<PointDTO>> response = pointController.findPointsByFunction(functionId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(points, response.getBody());
        verify(pointService, times(1)).findByFunction(functionId);
    }

    @Test
    void testFindPointsByFunction_NoContent() {
        long functionId = 1L;

        when(pointService.findByFunction(functionId)).thenReturn(null);

        ResponseEntity<List<PointDTO>> response = pointController.findPointsByFunction(functionId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(pointService, times(1)).findByFunction(functionId);
    }
}
