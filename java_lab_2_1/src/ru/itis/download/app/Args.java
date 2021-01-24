package ru.itis.download.app;
import com.beaust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Args {
    @Parameter(names={"--mode"})
    public String mode;

    @Parameter(names={"--count"})
    public Integer count;

    @Parameter(names={"--URL"})
    public ArrayList<String> URL;

    @Parameter(names={"--folder"})
    public String folder;

}
