document.getElementById('createTableButton').addEventListener('click', function() {
    const pointCount = parseInt(document.getElementById('pointCount').value);
    const tableBody = document.getElementById('dataTable').querySelector('tbody');

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

document.getElementById('createFunctionButton').addEventListener('click', function() {
    const xValues = Array.from(document.querySelectorAll('.x-value')).map(input => parseFloat(input.value));
    const yValues = Array.from(document.querySelectorAll('.y-value')).map(input => parseFloat(input.value));

    // Здесь вызовите фабрику для создания TabulatedFunction с xValues и yValues
    const tabulatedFunction = createTabulatedFunction(xValues, yValues);
    console.log(tabulatedFunction); // Логирование для проверки

    // Закрытие выпадающего окна (можно изменить для отображения успеха или других действий)
    document.getElementById('tableContainer').classList.add('hidden');
});

// Пример фабрики для создания TabulatedFunction
function createTabulatedFunction(xValues, yValues) {
    return { x: xValues, y: yValues };
}
