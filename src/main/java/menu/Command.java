package menu;

import tables.AnimalTable;

public enum Command {
    ADD,
    LIST,
    UPDATE,
    EXIT;


    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

