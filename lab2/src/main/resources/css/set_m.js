class FunctionFactory {
    createFunction() {
        return "Функция не определена";
    }
}

class ArrayFunctionFactory extends FunctionFactory {
    createFunction() {
        return "Табулированная функция на основе массива";
    }
}

class LinkedListFunctionFactory extends FunctionFactory {
    createFunction() {
        return "Табулированная функция на основе связного списка";
    }
}

let factory = new ArrayFunctionFactory(); // По умолчанию

document.getElementById('applyButton').addEventListener('click', function() {
    const selectedValue = document.getElementById('factorySelect').value;
    changeFactory(selectedValue);
    alert(factory.createFunction());
});

function changeFactory(factoryType) {
    if (factoryType === "array") {
        factory = new ArrayFunctionFactory();
    } else if (factoryType === "linkedList") {
        factory = new LinkedListFunctionFactory();
    }
}
function goBack() {
            // Возврат на главное окно
            window.location.href = 'MainMenu.html';