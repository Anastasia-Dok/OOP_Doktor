package database.service;

import database.DTO.MathFunctionsDTO;
import database.entity.MathFunctionsEntity;
import database.repositories.MathFunctionsRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class MathFunctionsServiceTest {
    private MathFunctionsService mathFunctionsService;
    private MathFunctionsRepository mathFunctionsRepository;
    @Test
    void testCreate() {
        MathFunctionsDTO dto = new MathFunctionsDTO();
        dto.setFunctionName("add");
        dto.setxTo(10.0);
        dto.setxFrom(0.0);
        dto.setCount(100);

        MathFunctionsEntity entity = new MathFunctionsEntity();
        entity.setFunctionName("add");
        entity.setxTo(10.0);
        entity.setxFrom(0.0);
        entity.setCount(100);

        when(mathFunctionsRepository.save(any(MathFunctionsEntity.class))).thenReturn(entity);

        MathFunctionsDTO result = mathFunctionsService.create(dto);

        assertNotNull(result);
        assertEquals("add", result.getFunctionName());
        assertEquals(10.0, result.getxTo());
        assertEquals(0.0, result.getxFrom());
        assertEquals(100, result.getCount());
    }

    @Test
    void testRead() {
        Long id = 1L;
        MathFunctionsEntity entity = new MathFunctionsEntity();
        entity.setId(id);
        entity.setFunctionName("add");
        entity.setxTo(10.0);
        entity.setxFrom(0.0);
        entity.setCount(100);

        when(mathFunctionsRepository.findById(id)).thenReturn(Optional.of(entity));

        MathFunctionsDTO result = mathFunctionsService.read(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("add", result.getFunctionName());
    }

    @Test
    void testUpdate() {
        MathFunctionsDTO dto = new MathFunctionsDTO();
        dto.setId(1L);
        dto.setFunctionName("add");
        dto.setxTo(10.0);
        dto.setxFrom(0.0);
        dto.setCount(100);

        MathFunctionsEntity entity = new MathFunctionsEntity();
        entity.setId(1L);
        entity.setFunctionName("add");
        entity.setxTo(10.0);
        entity.setxFrom(0.0);
        entity.setCount(100);

        when(mathFunctionsRepository.existsById(1L)).thenReturn(true);
        when(mathFunctionsRepository.save(any(MathFunctionsEntity.class))).thenReturn(entity);

        MathFunctionsDTO result = mathFunctionsService.update(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("add", result.getFunctionName());
    }

    @Test
    void testDelete() {
        MathFunctionsDTO dto = new MathFunctionsDTO();
        dto.setId(1L);

        when(mathFunctionsRepository.existsById(1L)).thenReturn(true);

        mathFunctionsService.delete(dto);

        verify(mathFunctionsRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        MathFunctionsEntity entity = new MathFunctionsEntity();
        entity.setId(id);
        entity.setFunctionName("add");

        when(mathFunctionsRepository.existsById(id)).thenReturn(true);
        when(mathFunctionsRepository.getById(id)).thenReturn(entity);

        MathFunctionsDTO result = mathFunctionsService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("add", result.getFunctionName());
    }

    @Test
    void testFindsByName() {
        String name = "add";
        MathFunctionsEntity entity = new MathFunctionsEntity();
        entity.setFunctionName(name);

        when(mathFunctionsRepository.findByFunctionName(name)).thenReturn(Collections.singletonList(entity));

        List<MathFunctionsDTO> result = mathFunctionsService.findsByName(name);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(name, result.get(0).getFunctionName());
    }

    @Test
    void testUpdateNonExistentFunction() {
        MathFunctionsDTO dto = new MathFunctionsDTO();
        dto.setId(999L);
        dto.setFunctionName("nonexistent");

        when(mathFunctionsRepository.existsById(dto.getId())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> mathFunctionsService.update(dto));
    }

    @Test
    void testDeleteNonExistentFunction() {
        MathFunctionsDTO dto = new MathFunctionsDTO();
        dto.setId(999L);

        when(mathFunctionsRepository.existsById(dto.getId())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> mathFunctionsService.delete(dto));
    }

    @Test
    void testReadNonExistentFunction() {
        Long id = 999L;

        when(mathFunctionsRepository.findById(id)).thenReturn(Optional.empty());

        MathFunctionsDTO result = mathFunctionsService.read(id);

        assert result == null; // Проверка на null, если функция не найдена
    }

    @Test
    void testGetByIdNonExistentFunction() {
        Long id = 999L;

        when(mathFunctionsRepository.existsById(id)).thenReturn(false);

        MathFunctionsDTO result = mathFunctionsService.getById(id);

        assert result == null; // Ожидаем null, если ID не существует
    }
}
