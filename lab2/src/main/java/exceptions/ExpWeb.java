package exceptions;

public class ExpWeb {

    public static void excp(Exception e) {
        // Логика для создания модального окна с сообщением об ошибке
        String message = generateErrorMessage(e);
        showModalDialog(message);
    }

    private static String generateErrorMessage(Exception e) {
        // Генерация читаемого сообщения об ошибке на основе типа исключения
        if (e instanceof IllegalArgumentException) {
            return "Ошибка: " + e.getMessage();
        } else if (e instanceof NumberFormatException) {
            return "Ошибка: Пожалуйста, введите корректное число.";
        }
        // Добавьте другие типы исключений по мере необходимости
        return "Неизвестная ошибка: " + e.getMessage();
    }

    private static void showModalDialog(String message) {
        // Код для отображения модального окна с сообщением об ошибке
        String script = "document.getElementById('errorMessage').innerText = '" + message + "';" +
                "$('#errorModal').modal('show');";
        executeJavaScript(script);
    }


    private static void executeJavaScript(String script) {
        // Реализация выполнения JavaScript на странице
    }


}

