package testclient;

import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannel;
import org.funstuff.secretcodes.Code;
import org.funstuff.secretcodes.EncodeRequest;
import org.funstuff.secretcodes.EncodeResponse;
import org.funstuff.secretcodes.SecretCodesServerGrpc;

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

        String message = "A proper sentence.";

        EncodeRequest request = EncodeRequest.newBuilder()
                .setMessage(message)
                .addCode(Code.SWAP_ENDS)
                .build();

        logger.info("Message I sent: " + request.getMessage());

        EncodeResponse response = stub.encode(request);
        logger.info("Response I get: " + response.getMessage());
    }
}


