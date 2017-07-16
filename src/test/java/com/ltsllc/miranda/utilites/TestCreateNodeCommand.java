package com.ltsllc.miranda.utilites;

import com.ltsllc.common.util.Utils;
import com.ltsllc.miranda.utilities.CreateNodeCommand;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by miranda on 7/12/2017.
 */
public class TestCreateNodeCommand {
    private CreateNodeCommand createNodeCommand;

    public CreateNodeCommand getCreateNodeCommand() {
        return createNodeCommand;
    }

    @Before
    public void setup () {
        createNodeCommand = new CreateNodeCommand();
    }
    @Test
    public void testGetPrivateKey () throws Exception {
        java.security.PrivateKey jsPrivateKey = Utils.loadEncryptedPrivateKey("ca.key", "whatever");
        java.security.PrivateKey junk = jsPrivateKey;
    }
}
