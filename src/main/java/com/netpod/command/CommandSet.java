package com.netpod.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandSet {
    private static final CommandSet COMMAND_SET = new CommandSet();

    private final Map<CommandName, Command> commandSet;

    private CommandSet() {
        Map<CommandName, Command> commandMap = createMapping();
        commandSet = Collections.unmodifiableMap(commandMap);
    }

    public static CommandSet singleton() {
        return COMMAND_SET;
    }

    public Command get(final CommandName pCommandName) {
        return commandSet.get(pCommandName);
    }

    private Map<CommandName, Command> createMapping() {
        Map<CommandName, Command> commandMap = new HashMap<>();
        commandMap.put(CommandName.USER, new UserCommand());
        commandMap.put(CommandName.PAYMENT, new PaymentCommand());
        commandMap.put(CommandName.PRODUCT, new ProductCommand());
        commandMap.put(CommandName.MENU, new MenuCommand());
        commandMap.put(CommandName.CANCEL, new CancelCommand());
        commandMap.put(CommandName.RESET, new ResetCommand());
        commandMap.put(CommandName.EXIT, new ExitCommand());

        return commandMap;
    }
}
