const differentiationModal = document.getElementById("differentiationWindow");
const differentiateButton = document.getElementById("differentiateButton");
const closeBtn = document.getElementsByClassName("close")[0];

// Открытие окна дифференцирования
btn.onclick = function() {
    differentiationModal.style.display = "block";
}

// Закрытие окна
closeBtn.onclick = function() {
    differentiationModal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == differentiationModal) {
        differentiationModal.style.display = "none";
    }
}

// Логика дифференцирования
differentiateButton.onclick = function() {
    // Получение данных из таблицы начальной функции
    const initialData = Array.from(document.querySelectorAll('#initialFunctionTable tbody tr')).map(row => {
        const cells = row.getElementsByTagName('td');
        return {
            x: parseFloat(cells[0].innerText),
            y: parseFloat(cells[1].innerText)
        };
    });

    // Вычисление производной
    const resultData = DifferentialOperator(initialData);

    // Отображение результата в таблице
    const resultBody = document.getElementById('resultFunctionTable').querySelector('tbody');
    resultBody.innerHTML = ''; // Очищаем предыдущие результаты

    resultData.forEach(point => {
        const newRow = document.createElement('tr');
        newRow.innerHTML = `<td>${point.x}</td><td>${point.derivative}</td>`;
        resultBody.appendChild(newRow);
    });
};

