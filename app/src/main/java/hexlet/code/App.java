
package hexlet.code;

import picocli.CommandLine;

public final class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        final CommandLine cmd = new CommandLine(new Differ());
        final CommandLine.ParseResult parseResult = cmd.parseArgs(args);
        if (!parseResult.errors().isEmpty()) {
            System.out.println(cmd.getUsageMessage());
        }
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }
}
