package cn.PApudding.Scanner;

import cn.PApudding.PipeLine.StorePaths;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class FileScanner implements Runnable {

    private String startPath = "";
    private ArrayList<String> directoryList = new ArrayList<>();
    private ArrayList<String> fileList = new ArrayList<>();

    public FileScanner(String startPath){
        this.startPath = startPath;
    }

    private void analyzePath(){
        File file = new File(startPath);
        File[] allFiles = file.listFiles();
        if(allFiles!=null) {
            for (File f : allFiles) {
                if (f.isDirectory()) {
                    directoryList.add(f.getPath());
                    System.out.println("找到目录："+f.getPath());
                } else if (f.isFile()) {
                    fileList.add(f.getPath());
                }
            }
        }
    }

    @Override
    public void run() {
        analyzePath();

        Thread subScanner;
        Thread storer;
        Iterator<String> iteratorD = directoryList.iterator();
        while (iteratorD.hasNext()){
            subScanner = new Thread(new FileScanner(iteratorD.next()));
            subScanner.start();
        }
        storer = new Thread(new StorePaths(fileList));
        storer.start();
    }
}
