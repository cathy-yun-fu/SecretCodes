package server;

import io.grpc.stub.StreamObserver;
import logic.Encoder;
import org.funstuff.secretcodes.*;

public class SecretCodeServiceImpl extends SecretCodesServerGrpc.SecretCodesServerImplBase {

    @Override
    public void decode(DecodeRequest request, StreamObserver<DecodeResponse> responseObserver) {
        throw new UnsupportedOperationException("Not implemented yet");

//        responseObserver.onNext(null);
//        responseObserver.onCompleted();
    }

    @Override
    public void encode(EncodeRequest request, StreamObserver<EncodeResponse> responseObserver) {
        Encoder encoder = new Encoder();
        System.out.println(request.getMessage());
        String encodedMessage = encoder.run(request.getMessage());

        EncodeResponse response = EncodeResponse.newBuilder().setMessage(encodedMessage).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
