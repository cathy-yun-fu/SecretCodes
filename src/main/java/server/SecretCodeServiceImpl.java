package server;

import io.grpc.stub.StreamObserver;
import logic.Orchestrator;
import org.funstuff.secretcodes.*;

import java.util.logging.Logger;

public class SecretCodeServiceImpl extends SecretCodesServerGrpc.SecretCodesServerImplBase {

    private static final Logger logger = Logger.getLogger(SecretCodeServiceImpl.class.getName());

    @Override
    public void decode(DecodeRequest request, StreamObserver<DecodeResponse> responseObserver) {
        throw new UnsupportedOperationException("Not implemented yet");

//        responseObserver.onNext(null);
//        responseObserver.onCompleted();
    }

    @Override
    public void encode(EncodeRequest request, StreamObserver<EncodeResponse> responseObserver) {
        if (request.getCodeCount() == 0) {
            throw new IllegalArgumentException("Missing argument: code");
        }

        Orchestrator encoder = new Orchestrator(request.getCodeList());
        logger.info("Code list " + request.getCodeList() + " for received message: " + request.getMessage());
        String encodedMessage = encoder.run(request.getMessage());

        EncodeResponse response = EncodeResponse.newBuilder().setMessage(encodedMessage).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
