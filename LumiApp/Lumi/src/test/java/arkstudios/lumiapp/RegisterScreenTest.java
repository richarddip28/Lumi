package arkstudios.lumiapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created by Richard Dip on 1/16/2018.
 */

public class RegisterScreenTest {

    private RegisterScreen registerScreen;

    @Before
    public void init() throws Exception{
        registerScreen = new RegisterScreen();
    }

    @Test
    public void registerOkTest() throws Exception{
        Assert.assertTrue(registerScreen.testing(true, true, true));
    }

    @Test
    public void registerFailTest() throws Exception{
        Assert.assertFalse(registerScreen.testing(false, true, true));
    }
}
