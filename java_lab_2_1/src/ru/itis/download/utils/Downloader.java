package ru.itis.download.utils;

import java.io.*;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader{
    public static void main(String[] args) {
        Downloader down = new Downloader("1", "C:\\images");
        down.download("https://i.pinimg.com/originals/f4/d2/96/f4d2961b652880be432fb9580891ed62.png",
                down.folder, "\\cat.png");
    }

    private String mode;
    private String folder;

    public Downloader(String mode, String folder) {

        this.mode = mode;
        this.folder = folder;
    }

    public boolean download(String strURL, String folder, String imgName) {
        InputStream in;
        OutputStream out;
        try {
            URL url = new URL(strURL);

            in = new BufferedInputStream(url.openStream());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        try {
            out = new BufferedOutputStream(new FileOutputStream(folder + imgName));
            for (int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return false;

    }


}
