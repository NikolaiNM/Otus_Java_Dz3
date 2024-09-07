package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputIntValidator {

    private Scanner scanner;

    public InputIntValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getValidInput(String hint, String paramMast, int min, int max) {
        int value = 0;
        boolean isValid = false;
        do {
            System.out.println(hint + " (число от " + min + " до " + max + ")" );
            try {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    isValid = true;
                }  else {
                    System.out.println(paramMast + " быть в диапазоне от " + min + " до " + max );
                }
            }catch (InputMismatchException e) {
                System.out.println("Вводите только цифры!");
                scanner.nextLine();
            }
        }while (!isValid);
        scanner.nextLine();
        return value;
    }

}
