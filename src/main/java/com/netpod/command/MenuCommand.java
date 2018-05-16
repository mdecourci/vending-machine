package com.netpod.command;

import com.netpod.Role;
import com.netpod.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuCommand implements Command {
    private User user = User.singleton();

    private Command nextCommand = null;

    @Override
    public CommandName getName() {
        return CommandName.MENU;
    }

    @Override
    public List<Option> options() {

        Scanner scanner = new Scanner(System.in);

        System.out.println( "1:Change user " );
        if (Role.CONSUMER == user.getCurrentRole()) {
            System.out.println( "2:Make payment " );
            System.out.println( "3:Cancel last request " );
            System.out.println( "4:Exit " );
        } else {
            System.out.println( "2:Reset vending machine" );
            System.out.println( "3:Exit " );
        }

        // get command
        int command = scanner.nextInt();

        return Arrays.asList(new Option(command));
    }

    @Override
    public void execute(final List<Option> pOptions) {
        Option<Integer> option = pOptions.get(0);

        CommandName commandName = null;
        switch (option.getParameter()) {
            case 1 :
                commandName = CommandName.USER;
                break;
            case 2 :
                if (Role.CONSUMER == user.getCurrentRole()) {
                    commandName = CommandName.PAYMENT;
                } else {
                    commandName = CommandName.RESET;
                }
                break;
            case 3 :
                if (Role.CONSUMER == user.getCurrentRole()) {
                    commandName = CommandName.CANCEL;
                } else {
                    commandName = CommandName.EXIT;
                }
                break;
            case 4 :
                commandName = CommandName.EXIT;
        }

        nextCommand = CommandSet.singleton().get(commandName);
    }

    @Override
    public Command next() {
        return nextCommand;
    }
}
