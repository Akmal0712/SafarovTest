package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sort {
    private static int countOfNumbers;
    private static int[] array;
    private static boolean typeOfSort = true;

    public static void main(String[] args) {
        System.out.println("Insert the number");
        auth();

        array = generateNewArray();
        showArray(array);

        actions();
    }

    public static void actions() {
        Scanner scanner = new Scanner(System.in);
        String keyWord = "";
        while(true) {
            System.out.println("1)Choose an action: Enter \"sort\" for sort; 2)Enter \"reset\" for reset; 3)Enter number for new Array;");
            keyWord = scanner.nextLine();

            if (keyWord.equals("reset")){
                main(new String[]{"Safarov"});
                break;
            }
            else if(keyWord.equals("sort")) {
                qSort(array, 0, countOfNumbers - 1);
                typeOfSort = !typeOfSort;
                showArray(array);
                actions();
                break;
            }
            else {
                try {
                    int finalKeyWord = Integer.parseInt(keyWord);
                    int res = Arrays.stream(array).filter(x -> x == finalKeyWord).findFirst().getAsInt();
                    if (res == finalKeyWord) {
                        if (Integer.parseInt(keyWord) <= 30) {
                            countOfNumbers = finalKeyWord;
                            array = generateNewArray();
                            showArray(array);
                            actions();
                        } else {
                            System.out.println("Insert a number less than 30");
                            actions();
                        }
                    }
                } catch (Exception e) {
                    actions();
                }
            }
        }
    }

    public static void auth() {
        Scanner number = new Scanner(System.in);
        try {
            countOfNumbers = number.nextInt();
            if (countOfNumbers > 0 && countOfNumbers <= 100 ) {
                return;
            }
            else {
                System.out.println("Insert a number less than 100 and more than 0");
                auth();
            }
        } catch (Exception e) {
            System.out.println("Insert the number");
            auth();
        }
    }

    public static int[] generateNewArray() {
        boolean numberLessThanThirty = false;

        Random items = new Random();
        int[] array = new int[countOfNumbers];
        for (int i = 0; i < countOfNumbers; i++) {
            array[i] = items.nextInt(1000);
            if (array[i] <= 30)
                numberLessThanThirty = true;
        }

        if (!numberLessThanThirty)
            return generateNewArray();

        return array;
    }

    public static void showArray(int[] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < countOfNumbers; j+=10) {
                System.out.print(array[j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void qSort(int[] array, int low, int high) {
        if (low >= high)
            return;
        int mid = low + (high - low) / 2;
        int centralElement = array[mid];

        int i = low, j = high;
        while(i <= j) {
            if (typeOfSort == true) {
                while(array[i] < centralElement)
                    i++;
                while (array[j] > centralElement)
                    j--;
            } else {
                while(array[i] > centralElement)
                    i++;
                while (array[j] < centralElement)
                    j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            qSort(array, low, j);
        if (high > i)
            qSort(array, i, high);
    }
}
