package nl.joeriabbo;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Integer timeout = 0;
        int concurrent = 0;

        System.out.println("Url to ping?");
        Scanner urlIn = new Scanner(System.in);
        String url = urlIn.nextLine();

        System.out.println("Timemout time?");
        Scanner timeoutIn = new Scanner(System.in);
        try {
            timeout = Integer.valueOf(timeoutIn.nextLine());
        } catch (Exception e) {
            System.out.println("Please try again with a Integer");
            System.exit(0);
        }

        System.out.println("Concurrent amount?");
        Scanner concurrentIn = new Scanner(System.in);
        try {
            concurrent = Integer.parseInt(concurrentIn.nextLine());
        } catch (Exception e) {
            System.out.println("Please try again with a Integer");
            System.exit(0);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(concurrent);
        for (int i = 0; i < concurrent; ++i) {
            executorService.submit(new RunnablePinger(url, timeout));
        }
    }
}
