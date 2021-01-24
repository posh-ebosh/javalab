package ru.itis;

//import ru.itis.download.utils.Downloader;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.beust.jcommander.JCommander;

import java.util.ArrayList;
public class Main {
    public static void main(String[] argv) {
        Date date = new Date();
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Integer count;
        String mode = args.mode;
        String folder = args.folder;

        if (mode.equals("one-thread")) {
            count = 1;
        } else {
            count = args.count;
        }

        List<String> strURL = args.URL;
        String name = args.name + date.getTime();
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        Downloader downloader = new Downloader();


        for (int i = 0; i < strURL.size(); i++) {
            int finalI = i;
            executorService.submit(() -> {
                downloader.download(strURL.get(finalI), folder, "\\" + name + ".png");
            });
        }
    }
}