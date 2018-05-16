package com.netpod.command;

import com.netpod.Role;
import com.netpod.User;

import java.util.*;

import static com.netpod.command.CommandSet.singleton;

public class UserCommand implements Command {
    private User user = User.singleton();

    @Override
    public CommandName getName() {
        return CommandName.USER;
    }

    @Override
    public List<Option> options() {
        Scanner scanner = new Scanner(System.in);

        System.out.println( "Select user; 1:consumer, 2:supplier" );
        // get user type
        Integer usertype = scanner.nextInt();

        return Collections.unmodifiableList(Arrays.asList(new Option(usertype)));
    }

    @Override
    public void execute(final List<Option> pOptions) {
        Option<Integer> option = pOptions.get(0);
        user.setRole(Role.findRole(option.getParameter()));
    }

    @Override
    public Command next() {
        return singleton().get(CommandName.MENU);
    }
}
