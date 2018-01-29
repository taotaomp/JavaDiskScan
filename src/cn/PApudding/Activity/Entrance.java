package cn.PApudding.Activity;

import cn.PApudding.Scanner.FileScanner;

import java.io.File;

public class Entrance {
    public static void main(String[] args){
        File file = new File("allFiles.txt");
        if(file.exists()){
            file.delete();
        }
        Thread scanner = new Thread(new FileScanner("/home"));
        scanner.start();
    }
}
