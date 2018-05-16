package com.netpod.command;

import java.util.List;

public class ExitCommand implements Command {
    @Override
    public CommandName getName() {
        return CommandName.EXIT;
    }

    @Override
    public List<Option> options() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(final List<Option> pOptions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command next() {
        throw new UnsupportedOperationException();
    }
}
