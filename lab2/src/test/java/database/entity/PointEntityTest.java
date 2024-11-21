package database.entity;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointEntityTest {

    @Test
    public void testPointEntityGettersAndSetters() {
        // Создаем объект PointEntity
        PointEntity point = new PointEntity();

        // Проверяем, что ID изначально равен null
        assertThat(point.getId()).isNull();

        // Устанавливаем и проверяем значение x
        point.setX(1.5);
        assertThat(point.getX()).isEqualTo(1.5);

        // Устанавливаем и проверяем значение y
        point.setY(2.5);
        assertThat(point.getY()).isEqualTo(2.5);

        // Устанавливаем и проверяем значение функции
        MathFunctionsEntity mathFunction = new MathFunctionsEntity();
        point.setFunction(mathFunction);
        assertThat(point.getFunction()).isEqualTo(mathFunction);
    }

    @Test
    public void testPointEntityIdSetterAndGetter() {
        // Создаем объект PointEntity
        PointEntity point = new PointEntity();

        // Устанавливаем ID
        point.setId(10L);

        // Проверяем, что ID был установлен корректно
        assertThat(point.getId()).isEqualTo(10L);
    }
}
