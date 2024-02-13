import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannel;

import java.io.IOException;
import java.util.logging.Logger;

public class ClientStartup {

    private static final Logger logger = Logger.getLogger(ClientStartup.class.getName());
    public static void main(String[] args) throws IOException, InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        SecretCodesServerGrpc.SecretCodesServerBlockingStub stub =
                SecretCodesServerGrpc.newBlockingStub(channel);

        EncodeRequest request = EncodeRequest.newBuilder()
                .setMessage("this is la di dah message a a a c c c !!woot is me")
                .build();

        EncodeResponse response = stub.encode(request);
        System.out.println("this is response: " + response.getMessage());
    }
}


