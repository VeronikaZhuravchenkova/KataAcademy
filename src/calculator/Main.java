package calculator;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static calculator.Roman.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример с использованием арабских или римских цифр");
        System.out.println("Разделяйте каждый операнд пробелом");
        while (true) {
            String input = scanner.nextLine();
            String result = calc(input);
            System.out.println(result);
        }
    }

    public static String calc(String input) throws IOException {
        String result = null;
        Pattern pattern = Pattern.compile("^[^\\s]*$");
        Matcher matcher = pattern.matcher(input);
        String[] example;
        if (matcher.matches()) {
            throw new IOException("операция должна быть введена через пробел");
        } else {
            example = input.split(" ");
        }
            if (example.length != 3)
                throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор");


            if (checkRoman(example[0], example[2])) {
                String num1 = example[0];
                int number1 = romanToNumber(num1);

                String operation = example[1];

                String num2 = example[2];
                int number2 = romanToNumber(num2);

                int resultArabian = calculate(number1, number2, operation);

                if (resultArabian < 1) {
                    throw new IOException("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
                } else {
                    result = convertNumToRoman(resultArabian);
                }

            } else if (!checkRoman(example[0], example[2])) {
                int number1 = Integer.parseInt(example[0]);
                String operation = example[1];
                int number2 = Integer.parseInt(example[2]);

                if (checkNums(number1, number2)) {
                    throw new IOException("Калькулятор работает с целыми числами от 1 до 10");
                } else {
                    int value = calculate(number1, number2, operation);
                    result = Integer.toString(value);
                }
            }
            return result;
        }

        /**
         * Метод проводит операцию с двумя числами
         *
         * @param number1   число1
         * @param number2   число2
         * @param operation математическая операция
         * @return результат
         * @throws IOException    нераспознанная операция
         */
        public static int calculate ( int number1, int number2, String operation) throws IOException {
            int result;

            switch (operation) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
                default:
                    throw new IOException("Операция не распознана");
            }
            return result;
        }

        /**
         * Метод проверяет диапазон чисел (1;10)
         *
         * @param number1 число1
         * @param number2 число2
         * @return результат проверки
         */
        public static boolean checkNums ( int number1, int number2){
            if ((number1 < 1) || (number1 > 10) || (number2 < 1) || (number2 > 10)) {
                return true;
            }
            return false;
        }
    }


/**
 * Класс работает с римскими числами
 */
class Roman {
    final static String[] ROMAN = {" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };


    /**
     * Метод проверяет, является ли число с консоли римским
     *
     * @param num римское  число
     * @return результат проверки
     */
    private static boolean isRoman(String num) {
        for (String s : ROMAN)
            if (num.equals(s)) {
                return true;
            }
        return false;
    }


    /**
     * Метод проверяет 2 числа с консоли, являются ли они римскими
     *
     * @param num1 число1
     * @param num2 число2
     * @return результат проверки
     * @throws IOException
     */
    public static boolean checkRoman(String num1, String num2) throws IOException {
        if (isRoman(num1) && isRoman(num2)) {
            return true;
        } else if (!isRoman(num1) && !isRoman(num2)) {
            return false;
        } else throw new IOException("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
    }


    /**
     * Метод конвертирует римское число в арабское
     *
     * @param roman римское число
     * @return результат в арабских цифрах
     */

    public static int romanToNumber(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }


    /**
     * Метод конвертирует арабское число в римское
     *
     * @param resultArabian арабское число
     * @return результат в римских цифрах
     */
    public static String convertNumToRoman(int resultArabian) {
        return ROMAN[resultArabian];
    }
}
