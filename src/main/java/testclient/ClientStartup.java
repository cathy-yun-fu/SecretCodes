package testclient;

import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.units.qual.C;
import org.funstuff.secretcodes.EncodeResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ClientStartup {

    private static final Logger logger = Logger.getLogger(ClientStartup.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
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
                    while(!future.isDone()) {}
                    logger.info("Response: " + future.get().getMessage());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        one.start();
        two.start();

        two.join();
        one.join();
    }
}


