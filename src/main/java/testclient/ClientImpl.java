package testclient;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.funstuff.secretcodes.Code;
import org.funstuff.secretcodes.EncodeRequest;
import org.funstuff.secretcodes.EncodeResponse;
import org.funstuff.secretcodes.SecretCodesServerGrpc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ClientImpl {

    private static final Logger LOGGER = LogManager.getLogger();

    private static SecretCodesServerGrpc.SecretCodesServerFutureStub stub;
    ClientImpl() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        stub = SecretCodesServerGrpc.newFutureStub(channel);
    }

    public ListenableFuture<EncodeResponse> sendRequestOne() {
        String message = "A proper sentence.";

        EncodeRequest request = EncodeRequest.newBuilder()
                .setMessage(message)
                .addCode(Code.SWAP_ENDS)
                .build();

        ListenableFuture<EncodeResponse> response = stub.encode(request);
        response.addListener(()->{
            try {
                LOGGER.info("Response: " + response.get().getMessage());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }, Executors.newSingleThreadExecutor());

        return response;
    }

    public ListenableFuture<EncodeResponse> sendRequestTwo() {
        String message = "whatis this - the second request - wee wee wee woot woot woot";

        EncodeRequest request = EncodeRequest.newBuilder()
                .setMessage(message)
                .addCode(Code.PIG_LATIN)
                .build();

        return stub.encode(request);
    }
}
