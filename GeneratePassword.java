/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.start;

import java.security.SecureRandom;

/**
 *
 * @author ASUS
 */
public class GeneratePassword {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%";


    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        // Generate at least one character from each category
        password.append(getRandomCharacter(CHARACTERS, random));
        password.append(getRandomCharacter(NUMBERS, random));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS, random));

        // Generate remaining characters randomly
        for (int i = 3; i < length; i++) {
            String characterSet = getRandomCharacterSet(random);
            password.append(getRandomCharacter(characterSet, random));
        }

        // Shuffle the password to make it more random
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(length);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(randomIndex));
            password.setCharAt(randomIndex, temp);
        }

        return password.toString();
    }

    private static char getRandomCharacter(String characterSet, SecureRandom random) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }

    private static String getRandomCharacterSet(SecureRandom random) {
        int randomIndex = random.nextInt(3);
        switch (randomIndex) {
            case 0:
                return CHARACTERS;
            case 1:
                return NUMBERS;
            case 2:
                return SPECIAL_CHARACTERS;
            default:
                throw new IllegalArgumentException("Invalid random index");
        }
    }
}
