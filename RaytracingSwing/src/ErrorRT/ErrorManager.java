package ErrorRT;

import GUI.GuiApp;

public class ErrorManager {
    public static void printError(String str) {
        System.out.println("Error: " + str);
        GuiApp.setError(str); //выводим ошибку в текстовое поле
    }
}
