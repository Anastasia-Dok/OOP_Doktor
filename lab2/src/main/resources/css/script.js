document.getElementById('createTableButton').addEventListener('click', function () {
    const pointCount = parseInt(document.getElementById('pointCount').value);
    const tableBody = document.getElementById('dataTable').querySelector('tbody');
    const errorMessage = document.getElementById('errorMessage'); // Убедитесь, что errorMessage определен

    // Проверяем количество точек
    if (pointCount < 2) {
        errorMessage.textContent = "Количество точек не должно быть меньше 2!";
        $('#errorModal').modal('show');
        return;
    }

    // Очищаем существующие строки в таблице
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }

    // Создаем новые строки в таблице
    for (let i = 0; i < pointCount; i++) {
        const newRow = document.createElement('tr');
        newRow.innerHTML = `<td><input type="number" class="x-value" required></td>
                            <td><input type="number" class="y-value" required></td>`;
        tableBody.appendChild(newRow);
    }

    // Показываем таблицу
    document.getElementById('tableContainer').classList.remove('hidden');
});

document.getElementById('createFunctionButton').addEventListener('click', async function () {
    const xValues = Array.from(document.querySelectorAll('.x-value')).map(input => parseFloat(input.value));
    const yValues = Array.from(document.querySelectorAll('.y-value')).map(input => parseFloat(input.value));

    // Проверка на корректность значений
    if (xValues.some(isNaN) || yValues.some(isNaN)) {
        alert("Все поля должны быть заполнены числовыми значениями!");
        return;
    }

    // Проверка на уникальность x-значений
    const uniqueX = new Set(xValues);
    if (uniqueX.size !== xValues.length) {
        alert("Значения X должны быть уникальными!");
        return;
    }

    // Подготовка данных для отправки на сервер
    const points = xValues.map((x, index) => ({
        x: x,
        y: yValues[index]
    }));

const mathFunctionDTO = {
        functionName: "y = x^2",   // Пример: название функции
        xFrom: 10,  // Минимальное значение x
        xTo: 1,    // Максимальное значение x
        count: 9,         // Количество точек
    };

    try {
        // Измените путь на тот, который соответствует вашему контроллеру для создания функции
        const response = await fetch('/functions/dto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(mathFunctionDTO)
        });
        console.log("Response status:", response.status);
        console.log("Response ok:", response.ok);
        console.log("Response body:", await response.text());


        if (response.ok) {
            alert("Функция успешно создана!");
            console.log("Серверный ответ:", await response.json());
        } else {
            alert("Ошибка при создании функции. Проверьте данные и попробуйте снова.");
            console.error("Ошибка:", response.status, await response.text());
        }
    } catch (error) {
        console.error("Ошибка подключения к серверу:", error);
        alert("Ошибка подключения к серверу.");
    }

    // Закрытие окна (по необходимости)
    document.getElementById('tableContainer').classList.add('hidden');
});

// Функция для локального создания TabulatedFunction
function createTabulatedFunction(xValues, yValues) {
    return { x: xValues, y: yValues };
}
