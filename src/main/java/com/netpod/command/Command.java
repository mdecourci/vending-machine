package com.netpod.command;

import java.util.List;
import java.util.Set;

public interface Command {
    CommandName getName();

    List<Option> options();

    void execute(List<Option> pOptions);

    Command next();
}
