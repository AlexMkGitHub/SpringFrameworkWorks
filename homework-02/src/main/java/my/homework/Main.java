package my.homework;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        productService.addProduct();
        productService.listCommand();

        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            System.out.print("Введите команду...");
            String command = sc.nextLine();
            System.out.println();

            if (command.equals("C") || command.equals("c")) {
                productService.listCommand();
            }
            if (command.equals("L") || command.equals("l")) {
                productService.listAllProduct();
            }
            if (command.equals("A") || command.equals("a")) {
                productService.addProductInCart();
            }
            if (command.equals("D") || command.equals("d")) {
                productService.deleteProductFromCart();
            }
            if (command.equals("P") || command.equals("p")) {
                productService.productInCart();
            }
        }
    }

}

