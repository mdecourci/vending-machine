package com.netpod.command;

import com.netpod.Order;
import com.netpod.Product;
import com.netpod.User;
import com.netpod.VendingMachine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductCommand implements Command {

    private VendingMachine vendingMachine = VendingMachine.singleton();
    private User user = User.singleton();

    @Override
    public CommandName getName() {
        return CommandName.PRODUCT;
    }

    @Override
    public List<Option> options() {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Select product(price): 1:CANDY (10), 2:SNACK (50), 3:NUTS (75), 4:Coke (150), 5:Bottle Water (100))" );
        // get product
        Integer product = scanner.nextInt();

        return Collections.unmodifiableList(Arrays.asList(new Option(product)));
    }

    @Override
    public void execute(final List<Option> pOptions) {
        Option<Integer> option = pOptions.get(0);

        Order order =  vendingMachine.orderProduct(user.getCurrentRole(), Product.findProduct(option.getParameter()));

        System.out.println(String.format("\nSelected product is %s with change %d pence\n", order.getProduct(), order.getChange()));
    }

    @Override
    public Command next() {
        return CommandSet.singleton().get(CommandName.MENU);
    }
}
