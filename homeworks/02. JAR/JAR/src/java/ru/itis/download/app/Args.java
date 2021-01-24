package ru.itis.download.app;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

public class Args {
    @Parameter(names={"--mode"})
    public String mode;

	
    @Parameter(names={"--count"}, converter = FileConverter.class)
    public Integer count;

    @Parameter(names={"--URL"})
    public ArrayList<String> URL = new ArrayList<>();

    @Parameter(names={"--folder"})
    public String folder;

}
