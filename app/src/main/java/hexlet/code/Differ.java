package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(
        name = "differ", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public class Differ {

    @CommandLine.Option(names = {"-f", "--format"},
            description = "output format [default: stylish]")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    public void generate() {
        //System.out.println("Command" + command);
    }
}
