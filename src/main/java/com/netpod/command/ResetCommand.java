package com.netpod.command;

import com.netpod.User;
import com.netpod.VendingMachine;

import java.util.List;

import static com.netpod.command.CommandSet.singleton;

public class ResetCommand implements Command {
    private VendingMachine vendingMachine = VendingMachine.singleton();
    private User user = User.singleton();

    @Override
    public CommandName getName() {
        return CommandName.RESET;
    }

    @Override
    public List<Option> options() {
        return null;
    }

    @Override
    public void execute(final List<Option> pOptions) {
        vendingMachine.reset(user.getCurrentRole());
    }

    @Override
    public Command next() {
        return singleton().get(CommandName.MENU);
    }
}
