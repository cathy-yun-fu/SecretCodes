package server;

import io.grpc.stub.StreamObserver;
import logic.Orchestrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.funstuff.secretcodes.*;

public class SecretCodeServiceImpl extends SecretCodesServerGrpc.SecretCodesServerImplBase {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void decode(DecodeRequest request, StreamObserver<DecodeResponse> responseObserver) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void encode(EncodeRequest request, StreamObserver<EncodeResponse> responseObserver) {
        if (request.getCodeCount() == 0) {
            throw new IllegalArgumentException("Missing argument: code");
        }

        Orchestrator encoder = new Orchestrator(request.getCodeList());
        LOGGER.info("Code list " + request.getCodeList() + " for received message: " + request.getMessage());
        String encodedMessage = encoder.run(request.getMessage());

        EncodeResponse response = EncodeResponse.newBuilder().setMessage(encodedMessage).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
