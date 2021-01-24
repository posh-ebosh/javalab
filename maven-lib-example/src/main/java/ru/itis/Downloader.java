package ru.itis;

import java.io.*;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader{


    private String mode;
    private String folder;

    public Downloader() {


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
