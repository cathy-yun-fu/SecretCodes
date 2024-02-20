package testclient;

import com.google.common.util.concurrent.ListenableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.funstuff.secretcodes.EncodeResponse;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ClientStartup {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            ArrayList<ListenableFuture<EncodeResponse>> responseFutures = new ArrayList<>();
            ClientImpl client = new ClientImpl();

            for (int i = 0; i < 10; i++) {
                responseFutures.add(client.sendRequestOne());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread two = new Thread(() -> {
            ArrayList<ListenableFuture<EncodeResponse>> responseFutures = new ArrayList<>();
            ClientImpl client = new ClientImpl();

            for (int i = 0; i < 10; i++) {
                responseFutures.add(client.sendRequestTwo());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (ListenableFuture<EncodeResponse> future : responseFutures ) {
                try {
                    String message = future.get(10, TimeUnit.SECONDS).getMessage();
                    LOGGER.info("Response: " + message );
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (TimeoutException e) {
                    LOGGER.info("Did not get response in time.");
                }
            }
        });

        one.start();
        two.start();

        two.join();
        one.join();
    }
}


