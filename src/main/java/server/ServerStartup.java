package server;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerStartup {

    private static final Logger LOGGER = LogManager.getLogger();

    public static int port = 50051;
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = Grpc.newServerBuilderForPort(port,
                            InsecureServerCredentials.create())
                        .addService(new SecretCodeServiceImpl())
                        .build()
                        .start();

        LOGGER.info("server started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println(" server is shutting down ");
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(" server shut down ");
        }));


        server.awaitTermination();
    }
}
