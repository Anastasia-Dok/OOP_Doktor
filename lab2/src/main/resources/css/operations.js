const modal = document.getElementById("operationWindow");
const btn = document.getElementById("openWindow");
const span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function createFunction(operand) {
    // Логика создания функции
    alert("Создать функцию для " + operand);
}

function uploadFunction(operand) {
    // Логика загрузки функции
    alert("Загрузить функцию для " + operand);
}

function saveFunction(operand) {
    // Логика сохранения функции
    alert("Сохранить функцию для " + operand);
}

// Обработчики событий для сохранения и загрузки функции
document.getElementById('saveFunctionButton').addEventListener('click', function() {
    const tabulatedFunction = createTabulatedFunction(
        Array.from(document.querySelectorAll('.x-value')).map(input => parseFloat(input.value)),
        Array.from(document.querySelectorAll('.y-value')).map(input => parseFloat(input.value))
    );

    const blob = new Blob([JSON.stringify(tabulatedFunction)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = url;
    a.download = 'tabulatedFunction.json'; // Название файла
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
});

document.getElementById('loadFunctionButton').addEventListener('change', function(event) {
    const file = event.target.files[0];

    const reader = new FileReader();
    reader.onload = function(e) {
        const loadedFunction = JSON.parse(e.target.result);
        const tableBody = document.getElementById('dataTable').querySelector('tbody');

        // Очищаем существующие строки в таблице
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        // Заполняем таблицу новыми значениями
        loadedFunction.x.forEach((x, index) => {
            const newRow = document.createElement('tr');
            newRow.innerHTML = `<td><input type="number" class="x-value" value="${x}" required></td>
                               <td><input type="number" class="y-value" value="${loadedFunction.y[index]}" required></td>`;
            tableBody.appendChild(newRow);
        });

        // Показываем таблицу
        document.getElementById('tableContainer').classList.remove('hidden');
    };

    reader.readAsText(file);
});

function performOperation(operation) {
    // Логика выполнения операций
    alert("Выполнить операцию: " + operation);
    // Здесь нужно использовать методы выбранного сервиса для обработки функций и обновления таблицы результата
}
