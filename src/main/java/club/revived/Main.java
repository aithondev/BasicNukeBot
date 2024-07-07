package club.revived;

import club.revived.boot.BotBootStrap;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("CallToPrintStackTrace")
public class Main {

    public static void main(String[] args) {
        File file = new File("token.txt");
        if(!file.exists()){
            try {
                if(file.createNewFile()) System.out.println("Token File has been created.");
                else System.out.println("Failed to create Token File.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String token = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            token = reader.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }

        BotBootStrap.start(token);
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if(next.equals("status")){
            System.out.println(Color.GREEN + "Ready");
        }
    }
}