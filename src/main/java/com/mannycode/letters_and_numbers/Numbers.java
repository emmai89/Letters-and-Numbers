package com.mannycode.letters_and_numbers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Numbers {
    
    private final int[] largeNum = {25,50,75,100};

    private ArrayList<Integer> numbers;
    private int targetNum;
    private int largeCount;

    public Numbers (){
        numbers = new ArrayList<Integer>();
        targetNum = 0;
        largeCount = 0;

    }

    public void userInput(){

        Scanner scanner = new Scanner(System.in);
        String choice = "";
        Random rn = new Random();
        int num;

        System.out.println("Enter 'L' for a large number or 'S' for a small number");

        do{
            System.out.print(6-numbers.size() + " numbers left: ");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("s")) {
                numbers.add(rn.nextInt(10 ) + 1);
            }
            else if (choice.equalsIgnoreCase("l")) {
                if (largeCount == 4){
                    System.out.print("You can onlt have 4 large numbers! please enter 'S'");
                }
                else{
                    do{
                        num = largeNum[rn.nextInt(4)];
                    }while(numbers.contains(num));
                    numbers.add(num);
                    largeCount++;
                }
            }
            else {
                System.out.print("Invalid choice! Enter 'v' for vowel or 'c' for consonant. ");
            }
                
            System.out.println("Generated letters: " +numbers); 
    
        }while (numbers.size() < 6);
    
    }
}
