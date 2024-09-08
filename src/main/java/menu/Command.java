package menu;

public enum Command {
    ADD,
    LIST,
    EXIT;


    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }


    }
}

