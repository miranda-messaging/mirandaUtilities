import com.ltsllc.example.CreateCertificateSigningRequest;
import com.ltsllc.example.Example;
import org.junit.jupiter.api.Test;

/**
 * Created by Clark on 6/30/2017.
 */
public class TestCreateCetificateSigningRequest {
    @Test
    public void testCreateCSR () throws Exception {
        Example example = new Example();
        example.createSignatureRequest();
    }

}
