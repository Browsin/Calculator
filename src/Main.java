import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner vvod = new Scanner(System.in);
        System.out.println("Введите выражение через пробел");

        System.out.println("Ответ: " + calc(vvod.nextLine())); // обращаясь к методу Calc получаем итог вычисления
    }
    public static String calc(String input) {
        String output; // Создадим строку для вывода результата вычисления

        int[] probel = new int[10];// Посчитаем колво пробелов и поставим условие не больше 2ух
        int j = 0;

        for(int i = 0;i<=input.length();i++) {
            int ind = input.indexOf(" ", i);
            i = ind;
            probel[j] = ind;
            if (ind == -1) {i = input.length();}
            j++;
        }
        if(probel[2] != -1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                output = "Введено недопустимое количество пробелов";
                return output;
            }
        }
        String stroka_a = input.substring(0,probel[0]); //создадим строки для записи чисел и знака из сканера
        String stroka_b = input.substring(probel[1]+1,input.length());
        String znak = input.substring(probel[0]+1,probel[1]);

        if((znak.length() != 1) || (znak.charAt(0) != '+' && znak.charAt(0) != '-' && //ставим условия правильности знака
                znak.charAt(0) != '/' && znak.charAt(0) != '*')) {
            try {
                throw new IOException();
            } catch (IOException e) {
                output = "Введена некорректно операция вычисления";
                return output;
            }

        }
        int rim = 0; //создадим переменную-счетчик Римских чисел
        int arab = 0; //создадим переменную-счетчик Арабских чисел
        String object = "0123456789IVX"; //и строку с удовлетворяемой выборкой значений введенных символов

        for(int i=stroka_a.length(); i>0; i--) { //определяем первое число
            if (object.indexOf(stroka_a.charAt(i-1)) == -1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    output = "Введены некорректные числа";
                    return output;
                }
            } else if (object.indexOf(stroka_a.charAt(i-1)) > 9) rim++; // условия ввода римского числа
            else arab++; // если введено арабское
        }

        for(int i=stroka_b.length(); i>0; i--) { // тоже самое для второго числа
            if (object.indexOf(stroka_b.charAt(i-1)) == -1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    output = "Введены некорректно цифры";
                    return output;
                }
            } else if (object.indexOf(stroka_b.charAt(i-1)) > 9) rim++;
            else arab++;
        }
        if(arab != 0 && rim !=0 ) { // проверяем? что числа одного формата
            try {
                throw new IOException();
            } catch (IOException e) {
                output = "Введены разные форматы цифр";
                return output;
            }
        }
        int itog = 0; // задали переменную итогового ответа

        if(arab !=0) { //если арабские числа
            int a_arab = Integer.parseInt(stroka_a);
            int b_arab = Integer.parseInt(stroka_b);
            if(a_arab > 10 || b_arab > 10) { // условие, что числа меньше 10
                try {
                    throw new IOException();
                } catch (IOException e) {
                    output = "Введены числа больше 10";
                    return output;
                }
            } else {
                switch (znak) {
                    case "+" :
                        itog = a_arab+b_arab;
                        break;
                    case "-" :
                        itog = a_arab-b_arab;
                        break;
                    case "*" :
                        itog = a_arab*b_arab;
                        break;
                    case "/" :
                        itog = a_arab/b_arab;
                        break;
                }
            output = String.valueOf(itog); // Результат вычисления в случае арабских чисел
            }
        } else { // если римские числа
            int index_rim_a =-1;
            int index_rim_b =-1;
            int index_rim_itog = 0;
            //создадим массив строк с римскими числами от 0 до 100 (код выглядит невоспитанно, но другой вариант у меня не скомпилировался)
            String [] object_rim = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X",//строка с индексами риских чисел 0 - 10
                    "XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX", // 11-20
                    "XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX", //21-30
                    "XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL", //31-40
                    "XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L", //41-50
                    "LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX", //51-60
                    "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX", //61-70
                    "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX", //71-80
                    "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC", //81-90
                    "XCI","XCII","XCIII","XCIV","XCV","XCI","XCVII","XCVIII","XCIX","C"}; //91-100
            for(int i=0; i<11; i++) { // ищем в данном массиве совпадения с введенными числами
                if (object_rim[i].compareTo(stroka_a) == 0) {
                    index_rim_a = i; // присваиваем индекс, тк номер индекса совпадаем со значением
                }
                if (object_rim[i].compareTo(stroka_b) == 0) {
                    index_rim_b = i;
                }

            }
            if(index_rim_a == -1 || index_rim_b == -1) { // если ввели абракадабру, а не римские числа
                try {
                    throw new IOException();
                } catch (IOException e) {
                    output = "Римские числа введены некорректно";
                    return output;
                }
            } else {
                switch (znak) {
                    case "+":
                        index_rim_itog = index_rim_a + index_rim_b;
                        break;
                    case "-":
                        index_rim_itog = index_rim_a - index_rim_b;
                        break;
                    case "*":
                        index_rim_itog = index_rim_a * index_rim_b;
                        break;
                    case "/":
                        index_rim_itog = index_rim_a / index_rim_b;
                        break;
                }
                if(index_rim_itog < 0) {//условие неотрицательности результата
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        output = "Получено отрицательное значение римского числа";
                        return output;
                    }
                }
                output = object_rim[index_rim_itog]; // результат вычисления в случае римских чисел

            }
        }
        return output; //возвращаемое значение метода calc в виде строки в метод Main
    }
}
