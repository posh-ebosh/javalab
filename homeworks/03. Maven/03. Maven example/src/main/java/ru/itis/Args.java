package ru.itis;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

public class Args {
    @Parameter(names={"--mode"})
    public String mode;

	
    @Parameter(names={"--count"})
    public Integer count;

    @Parameter(names={"--URL"}, variableArity = true)
    public List<String> URL = new ArrayList<>();

    @Parameter(names={"--folder"})
    public String folder;

    @Parameter(names={"--name"})
    public String name;

}
