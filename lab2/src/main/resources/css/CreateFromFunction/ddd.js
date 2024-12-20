//// Маппинг реализованных функций
//const mathFunctions = new Map([
//    ['Константная функция', new ConstantFunction(1)], // Пример с value = 1
//    ['Функция нуля', new ZeroFunction()],
//    ['Единичная функция', new UnitFunction()],
//    ['Квадратичная функция', new SqrFunction()],
//]);
//
//// Заполнение выпадающего списка
//const functionSelect = document.getElementById('functionSelect');
//Array.from(mathFunctions.keys())
//    .sort() // Сортировка по алфавиту
//    .forEach(name => {
//        const option = document.createElement('option');
//        option.value = name;
//        option.textContent = name;
//        functionSelect.appendChild(option);
//    });
//
//document.getElementById('createFunctionFromMathButton').addEventListener('click', function () {
//    try {
//        const selectedFunctionName = functionSelect.value;
//        const selectedFunction = mathFunctions.get(selectedFunctionName);
//
//        if (!selectedFunction) {
//            throw new Error('Выберите корректную функцию.');
//        }
//
//        const intervalStart = parseFloat(document.getElementById('intervalStart').value);
//        const intervalEnd = parseFloat(document.getElementById('intervalEnd').value);
//        const pointCount = parseInt(document.getElementById('pointCountMath').value);
//
//        if (isNaN(intervalStart) || isNaN(intervalEnd) || isNaN(pointCount)) {
//            throw new Error('Все значения должны быть числами.');
//        }
//
//        if (intervalStart >= intervalEnd) {
//            throw new Error('Начало интервала должно быть меньше конца.');
//        }
//
//        if (pointCount < 2) {
//            throw new Error('Количество точек должно быть не меньше 2.');
//        }
//
//        const step = (intervalEnd - intervalStart) / (pointCount - 1);
//        const xValues = Array.from({ length: pointCount }, (_, i) => intervalStart + i * step);
//        const yValues = xValues.map(x => selectedFunction.apply(x)); // Используем метод apply()
//
//        const tabulatedFunction = createTabulatedFunction(xValues, yValues);
//        console.log(tabulatedFunction); // Отладка
//
//        alert('Функция успешно создана!');
//    } catch (error) {
//        showErrorModal(error.message);
//    }
//});


document.addEventListener('DOMContentLoaded', function () {
    // Маппинг функций
    const mathFunctions = new Map([
        ['Константная функция', x => 1],
        ['Функция нуля', x => 0],
        ['Единичная функция', x => x],
        ['Квадратичная функция', x => x * x],
    ]);

    // Заполнение выпадающего списка
    const functionSelect = document.getElementById('functionSelect');
    if (functionSelect) {
        Array.from(mathFunctions.keys())
            .sort() // Сортируем функции по алфавиту
            .forEach(name => {
                const option = document.createElement('option');
                option.value = name;
                option.textContent = name;
                functionSelect.appendChild(option);
            });
    }



    // Обработчик для создания функции из математической функции
    document.getElementById('createFunctionFromMathButton').addEventListener('click', function () {
        try {
            const selectedFunctionName = functionSelect.value;
            const selectedFunction = mathFunctions.get(selectedFunctionName);

            if (!selectedFunction) {
                throw new Error('Выберите корректную функцию.');
            }

            const intervalStart = parseFloat(document.getElementById('intervalStart').value);
            const intervalEnd = parseFloat(document.getElementById('intervalEnd').value);
            const pointCount = parseInt(document.getElementById('pointCountMath').value);

            if (isNaN(intervalStart) || isNaN(intervalEnd) || isNaN(pointCount)) {
                throw new Error('Все значения должны быть числами.');
            }

            if (intervalStart >= intervalEnd) {
                throw new Error('Начало интервала должно быть меньше конца.');
            }

            if (pointCount < 2) {
                throw new Error('Количество точек должно быть не меньше 2.');
            }

            const step = (intervalEnd - intervalStart) / (pointCount - 1);
            const xValues = Array.from({ length: pointCount }, (_, i) => intervalStart + i * step);
            const yValues = xValues.map(selectedFunction);

            const tabulatedFunction = createTabulatedFunction(xValues, yValues);
            console.log(tabulatedFunction);

            alert('Функция успешно создана!');
        } catch (error) {
            showErrorModal(error.message);
        }
    });

    // Фабрика для создания TabulatedFunction
    function createTabulatedFunction(xValues, yValues) {
        return { x: xValues, y: yValues };
    }

    // Функция для отображения ошибок
    function showErrorModal(message) {
        const errorMessage = document.getElementById('errorMessage');
        if (errorMessage) {
            errorMessage.textContent = message;
            $('#errorModal').modal('show');
        }
    }
});
