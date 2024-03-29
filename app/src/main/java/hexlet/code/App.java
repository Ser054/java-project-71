
package hexlet.code;

import picocli.CommandLine;


import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "differ", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public final class App implements Callable<Integer> {
    @CommandLine.Option(names = {"-f", "--format"},
            description = "output format [default: stylish]")
    private String format;
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filePath1;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filePath2;

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        final CommandLine cmd = new CommandLine(new App());
        final CommandLine.ParseResult parseResult = cmd.parseArgs(args);
        if (!parseResult.errors().isEmpty()) {
            System.out.println(cmd.getUsageMessage());
        }
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            if (format == null || !(format.toLowerCase().equals("plain") || format.toLowerCase().equals("json"))) {
                format = "stylish";
            }
            System.out.println("Command: " + format + " " + filePath1 + " " + filePath2);
            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (Exception ex) {
            System.out.println("Error!\n" + ex.getMessage());
        }
        return 1;
    }
}
