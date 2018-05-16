package com.netpod.command;

import com.netpod.User;
import com.netpod.VendingMachine;

import java.util.*;
import java.util.stream.Collectors;

import static com.netpod.command.CommandSet.singleton;

public class PaymentCommand implements Command {

    private VendingMachine vendingMachine = VendingMachine.singleton();
    private User user = User.singleton();

    @Override
    public CommandName getName() {
        return CommandName.PAYMENT;
    }

    @Override
    public List<Option> options() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Make payment for CANDY = 10p, SNACK = 50p, NUTS = 75p, Coke = 150p, Bottle Water = 100p" );
        System.out.println( "Accepted coins in pence are : 1, 5, 20, 50, 100" );

        List<Option> options = new ArrayList<>();

        // get amount
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            Integer amount = Integer.valueOf(line.trim());
            Option<Integer> option = new Option<>(amount);
            options.add(option);
        }

        return Collections.unmodifiableList(options);
    }

    @Override
    public void execute(final List<Option> pOptions) {
        vendingMachine.payment(user.getCurrentRole(), pOptions.stream().mapToInt(p -> (Integer) p.getParameter()).boxed().collect(Collectors.toList()));
    }

    @Override
    public Command next() {
        return singleton().get(CommandName.PRODUCT);
    }
}
