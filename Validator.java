/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.start;

/**
 *
 * @author ASUS
 */
public class Validator {
    
    public static boolean empty(String arg){
        if (arg.isEmpty()){
            return true;
        }
        return false;
    }
    
    public static boolean lengthCheck(String arg, int len){
        if (arg.length() < len){
            return true;
            
        }
        return false; 
    }
    

    public static boolean containsNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    
    public static boolean containsSpecial(String input) {
        for (char c : input.toCharArray()) {
            if (c == ' ')
                continue;
            if (!Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }
    
    
    
}
