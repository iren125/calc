import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};

        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }

        if (actionIndex == -1) {
            throw new Exception("Некорректное выражение");
        }

        String[] data = input.split(regexActions[actionIndex]);
        if (data.length != 2) {
            throw new Exception("Некорректное выражение");
        }

        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    throw new Exception("Числа должны быть в диапазоне от 1 до 10");
                }
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    throw new Exception("Числа должны быть в диапазоне от 1 до 10");
                }
            }

            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (result < 1 && isRoman) {
                throw new Exception("Результат не может быть меньше 1 для римских чисел");
            }

            if (isRoman) {
                return converter.intToRoman(result);
            } else {
                return String.valueOf(result);
            }
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
    }
}