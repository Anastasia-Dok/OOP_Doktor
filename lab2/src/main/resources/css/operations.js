const API_BASE_URL = "/functions"; // Базовый URL для взаимодействия с контроллером

// Создание новой функции
function createFunction(operand) {
    const table = document.getElementById(`${operand}FunctionTable`);
    const xValues = Array.from(table.querySelectorAll("tbody tr td:first-child input")).map(input => parseFloat(input.value));
    const yValues = Array.from(table.querySelectorAll("tbody tr td:last-child input")).map(input => parseFloat(input.value));

    const mathFunction = { x: xValues, y: yValues, name: `${operand}Function` };

    fetch(`${API_BASE_URL}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(mathFunction),
    })
        .then(response => response.json())
        .then(data => {
            alert(`Функция создана с ID: ${data.id}`);
        })
        .catch(error => {
            console.error("Ошибка создания функции:", error);
        });
}

// Загрузка функции из сервера
function uploadFunction(operand) {
    const functionId = prompt("Введите ID функции для загрузки:");
    if (!functionId) return;

    fetch(`${API_BASE_URL}/${functionId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Функция не найдена");
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById(`${operand}FunctionTable`).querySelector("tbody");

            // Очищаем таблицу
            while (tableBody.firstChild) {
                tableBody.removeChild(tableBody.firstChild);
            }

            // Заполняем таблицу
            data.x.forEach((x, index) => {
                const newRow = document.createElement("tr");
                newRow.innerHTML = `<td><input type="number" value="${x}" readonly></td>
                                    <td><input type="number" value="${data.y[index]}" readonly></td>`;
                tableBody.appendChild(newRow);
            });

            alert(`Функция ${data.name} загружена.`);
        })
        .catch(error => {
            console.error("Ошибка загрузки функции:", error);
        });
}

// Сохранение функции на сервер
function saveFunction(operand) {
    const table = document.getElementById(`${operand}FunctionTable`);
    const xValues = Array.from(table.querySelectorAll("tbody tr td:first-child input")).map(input => parseFloat(input.value));
    const yValues = Array.from(table.querySelectorAll("tbody tr td:last-child input")).map(input => parseFloat(input.value));
    const functionId = prompt("Введите ID функции для обновления:");

    if (!functionId) {
        alert("ID не указан. Создание новой функции.");
        createFunction(operand);
        return;
    }

    const mathFunction = { id: parseInt(functionId), x: xValues, y: yValues, name: `${operand}Function` };

    fetch(`${API_BASE_URL}/${functionId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(mathFunction),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Ошибка обновления функции");
            }
            return response.json();
        })
        .then(data => {
            alert(`Функция с ID ${data.id} обновлена.`);
        })
        .catch(error => {
            console.error("Ошибка сохранения функции:", error);
        });
}

// Удаление функции
function deleteFunction() {
    const functionId = prompt("Введите ID функции для удаления:");
    if (!functionId) return;

    fetch(`${API_BASE_URL}/${functionId}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert(`Функция с ID ${functionId} удалена.`);
            } else {
                alert("Функция не найдена.");
            }
        })
        .catch(error => {
            console.error("Ошибка удаления функции:", error);
        });
}

// Выполнение операций (локальная обработка)
function performOperation(operation) {
    const firstTable = document.getElementById("firstFunctionTable");
    const secondTable = document.getElementById("secondFunctionTable");
    const resultTable = document.getElementById("resultFunctionTable").querySelector("tbody");

    const firstX = Array.from(firstTable.querySelectorAll("tbody tr td:first-child input")).map(input => parseFloat(input.value));
    const firstY = Array.from(firstTable.querySelectorAll("tbody tr td:last-child input")).map(input => parseFloat(input.value));
    const secondX = Array.from(secondTable.querySelectorAll("tbody tr td:first-child input")).map(input => parseFloat(input.value));
    const secondY = Array.from(secondTable.querySelectorAll("tbody tr td:last-child input")).map(input => parseFloat(input.value));

    if (firstX.length !== secondX.length || !firstX.every((x, index) => x === secondX[index])) {
        alert("Операции возможны только для функций с одинаковыми x-координатами.");
        return;
    }

    const resultY = firstY.map((y, index) => {
        switch (operation) {
            case "add":
                return y + secondY[index];
            case "subtract":
                return y - secondY[index];
            case "multiply":
                return y * secondY[index];
            case "divide":
                if (secondY[index] === 0) {
                    alert("Деление на ноль!");
                    return NaN;
                }
                return y / secondY[index];
            default:
                alert("Неизвестная операция: " + operation);
                return NaN;
        }
    });

    // Очищаем таблицу результата
    while (resultTable.firstChild) {
        resultTable.removeChild(resultTable.firstChild);
    }

    // Заполняем таблицу результата
    firstX.forEach((x, index) => {
        const newRow = document.createElement("tr");
        newRow.innerHTML = `<td>${x}</td><td>${resultY[index]}</td>`;
        resultTable.appendChild(newRow);
    });
}
