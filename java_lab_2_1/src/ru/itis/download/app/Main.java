package ru.itis.download.app;

import ru.itis.download.utils.Downloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
public class Main {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Integer count;
        String mode = args.mode;
        String folder = "C:\\images";

        if (mode.equals("one-thread")) {
            count = 1;
        } else {
            count = args.count;
        }

        ArrayList<String> strURL = args.URL;

        ExecutorService executorService = Executors.newFixedThreadPool(count);

        Downloader downloader = new Downloader(mode, folder);

        int name = 0;
        for (int i = 0; i < strURL.size(); i++) {
            System.out.println(name);
            int finalName = name;
            int finalI = i;
            executorService.submit(() -> {
                downloader.download(strURL.get(finalI), folder, "\\cat" + finalName + ".png");
            });
            name++;

        }
    }
}
