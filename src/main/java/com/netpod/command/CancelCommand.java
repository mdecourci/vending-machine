package com.netpod.command;

import com.netpod.User;
import com.netpod.VendingMachine;

import java.util.List;

public class CancelCommand implements Command {
    private VendingMachine vendingMachine = VendingMachine.singleton();
    private User user = User.singleton();

    @Override
    public CommandName getName() {
        return CommandName.CANCEL;
    }

    @Override
    public List<Option> options() {
        return null;
    }

    @Override
    public void execute(final List<Option> pOptions) {
        List<Integer> refundList = vendingMachine.cancel(user.getCurrentRole());

        int totalRefund = 0;
        if (refundList != null && !refundList.isEmpty()) {
            totalRefund = refundList.stream().reduce(0, Integer::sum);
        }

        System.out.println(String.format("Selection cancelled, refund is %d\n", totalRefund));
    }

    @Override
    public Command next() {
        return CommandSet.singleton().get(CommandName.MENU);
    }
}
