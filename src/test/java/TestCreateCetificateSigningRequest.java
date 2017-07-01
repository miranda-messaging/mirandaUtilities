import com.ltsllc.miranda.utilities.Utilities;
import org.junit.jupiter.api.Test;

/**
 * Created by Clark on 6/30/2017.
 */
public class TestCreateCetificateSigningRequest {
    @Test
    public void testCreateCSR () throws Exception {
        Utilities utilities = new Utilities();
        utilities.createSignatureRequest();
    }

}
