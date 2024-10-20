package logic.codes;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.PredefinedAeadParameters;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;

// wip
public class SymmetricEncryption extends Code {

    KeysetHandle keysetHandle;
    Aead aead;

    // todo: allow rpc to take associated data, and return bytes

    public SymmetricEncryption() {
        try {
            AeadConfig.register();
            keysetHandle = KeysetHandle.generateNew(PredefinedAeadParameters.AES256_GCM);
            aead = keysetHandle.getPrimitive(Aead.class);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }


    public String morph(String input) {
        // todo: ths needs to handle byte[] returns
        return super.morph(input);
    }

    public String decipher(String input) {
        return super.decipher(input);
    }
}
