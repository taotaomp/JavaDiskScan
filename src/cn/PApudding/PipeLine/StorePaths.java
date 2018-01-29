package cn.PApudding.PipeLine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StorePaths implements Runnable {
    public ArrayList<String> waitingForStore;
    private File file = new File("allFiles.txt");
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private Iterator<String> iterator;

    public StorePaths(ArrayList<String> waitingForStore) {
        this.waitingForStore = waitingForStore;
    }

    private void initialTools() throws IOException {
        fileWriter = new FileWriter(file,true);
        bufferedWriter = new BufferedWriter(fileWriter);
        iterator = waitingForStore.iterator();
        System.out.println("初始化工具");
    }

    private void storeFilePaths() throws IOException {
        while (iterator.hasNext()) {
            String temp = iterator.next();
            System.out.println("存储：" + temp);
            bufferedWriter.write(temp);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public void run() {
        try {
            initialTools();
            storeFilePaths();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
