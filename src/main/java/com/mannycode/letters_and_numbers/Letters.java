package com.mannycode.letters_and_numbers;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public class Letters {
    
    private final String vowls = "aeiou";
    private final String consonants = "bcdfghjklmnpqrstvwxyz";
    
    private Set<String> options;
    private ArrayList<String> longetst;
    private ArrayList<String> letters;
    private Set<String> dictionary;

    public Letters() {
        letters = new ArrayList<String>();
        dictionary = FileIO.readDictionary();
        options = new HashSet<>();
        longetst = new ArrayList<>();
        longetst.add("");
    }

    // Methods

    public void userInput(){
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        System.out.println("Enter 'v' for vowel or 'c' for consonant");

        do{
            System.out.print(9-letters.size() + " letters left: ");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("v")) {
                if(checkVowls())
                    letters.add(findVowl());
            } else if (choice.equalsIgnoreCase("c")) {
                if(checkConsonants())
                    letters.add(findConsonant());
            } else {
                 System.out.print("Invalid choice! Enter 'v' for vowel or 'c' for consonant. ");
            }
            
            System.out.println("Generated letters: "+ getLetters()); 

        }while (letters.size() < 9);

        findOptions();
    }

    public void userGuess(){

        Scanner scanner = new Scanner(System.in);
        String guess = ""; 
        boolean end = false;

        do {
            System.out.println("What is the longest word you can make?: ");
            guess = scanner.nextLine();
            guess = guess.trim().toLowerCase();
            if (options.contains(guess)) {
                if (guess.length() == longetst.get(0).length()) {
                    System.out.println("Correct! " +guess +" is the longest word at " +longetst.get(0).length() +" letters");
                    if (longetst.size() > 1) {
                        System.out.println("These are all the longest options: " +longetst);
                    }
                    end = true;
                }
                else{
                    System.out.println("Good guess! But there is a longer word!");
                    System.out.println("Do you want to try again? ");
                    do {
                        System.out.print("Enter 'y' for yes or 'n' for no: ");
                        guess = scanner.nextLine().trim();
                    } while (!guess.equalsIgnoreCase("y") && !guess.equalsIgnoreCase("n"));
                    
                    if (guess.equalsIgnoreCase("n")) {
                        end = true;
                        System.out.println("The longest word(s): " +longetst +" with " +longetst.get(0).length() +" letters");
                    }
                }
            }
            else {
                System.out.println("This word doesn't exist or is not in the list of options. Try again!");
            }
    
        } while (!end);

        scanner.close();
    }

    // Getters
    
    public ArrayList<String> getLetters(){
        return letters;
    }

    public Set<String> getOptions()
    {
        return options;
    }

    public ArrayList<String> getLongestWords()
    {
        return longetst;
    }

    //Private methods

    public void findOptions(String chars)
    {
        permute(chars);
        findLongestWord();
    }
    
    private void findOptions()
    {
        permute(String.join("", letters));
        findLongestWord();
    }


    private Set<String> permute(String chars)
    {
        Set<String> set = new TreeSet<String>();

        if (chars.length() == 1)
        {
            set.add(chars);
        }
        else
        {
            for (int i=0; i<chars.length(); i++)
            {
                String pre = chars.substring(0, i);
                String post = chars.substring(i+1);
                String remaining = pre+post;

                for (String permutation : permute(remaining))
                {
                    if (dictionary.contains(chars.charAt(i) + permutation)) {
                        options.add(chars.charAt(i) + permutation);
                    }
                    set.add(chars.charAt(i) + permutation);
                }
            }
        }
        return set;
    }

    private boolean checkVowls(){
        boolean valid = true;

        int vowlCount = 0;
        for (String letter : letters) {
            if (vowls.contains(letter)) {
                vowlCount++;
            }
        }

        if (vowlCount == 5 && letters.size() < 9) {
            System.out.print("You Need at least 4 consonants. Enter 'c' for consonant: ");
            valid = false;
        }

        return valid;
    }

    private boolean checkConsonants(){
        boolean valid = true;

        int consonantCount = 0;
        for (String letter : letters) {
            if (consonants.contains(letter)) {
                consonantCount++;
            }
        }

        if (consonantCount == 6 && letters.size() < 9) {
            System.out.print("You Need at least 3 vowels. Enter 'v' for voweL: ");
            valid = false;
        }

        return valid;
    }

    private String findVowl() {
        int randomIndex = (int) (Math.random() * 5);
        return vowls.substring(randomIndex, randomIndex + 1);
    }

    private String findConsonant() {
        int randomIndex = (int) (Math.random() * 21);
        return consonants.substring(randomIndex, randomIndex + 1);
    }

    private void findLongestWord(){
        for (String word : options) {
            for (ListIterator<String> temp = longetst.listIterator(); temp.hasNext();) {
                String tempWord = temp.next();
                if (word.length() > tempWord.length()) {
                   if(longetst.contains(word)){
                        temp.remove();
                    }
                    else{
                        temp.remove();
                        temp.add(word);
                    }
                }
                else if (word.length() == tempWord.length()) {
                    if(!longetst.contains(word)){
                        temp.add(word);
                    }
                }
            }
        }
    }   
}