
package com.task;

import java.util.Scanner;

public class Data {
    private final String username;
    private final String password;

    public Data(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

   
    private static String inputValidUsername(Scanner sc) {
        while (true) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            try {
                Validator.validateUsername(username);
                return username; 
            } catch (Exceptions.InvalidUsernameException e) {
                System.out.println("Username Error: " + e.getMessage());
               
            }
        }
    }

    
    private static String inputValidPassword(Scanner sc) {
        while (true) {
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            try {
                Validator.validatePassword(password);
                return password; 
            } catch (Exceptions.InvalidPasswordException e) {
                System.out.println("Password Error: " + e.getMessage());
                
            }
        }
    }


    private static String inputMatchingConfirmPassword(Scanner sc, String password) {
        while (true) {
            System.out.print("Confirm password: ");
            String confirm = sc.nextLine();
            try {
                Validator.validatePasswordMatch(password, confirm);
                return confirm;
            } catch (Exceptions.InvalidPasswordException e) {
                System.out.println("Confirm Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

      
        String username = inputValidUsername(sc);
        String password = inputValidPassword(sc);
        String confirm = inputMatchingConfirmPassword(sc, password);

        sc.close();

        
        Data data = new Data(username, password);
        System.out.println("All good! Username and password are valid.");
    }
}
