package com.netpod.command;

import java.util.Set;

import static com.netpod.command.CommandSet.singleton;

public class CommandProcessor {

    public static void main(String[] args) {
        Command command = singleton().get(CommandName.USER);

        while (command.getName() != CommandName.EXIT) {
            command.execute(command.options());
            command = command.next();
        }
    }
}
