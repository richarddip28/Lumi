package arkstudios.lumiapp;

import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

/**
 * Created by Richard Dip on 1/15/2018.
 */

public class LoginScreenTest {

    private LoginScreen loginScreen;

    @Before
    public void init() throws Exception{
        loginScreen = new LoginScreen();
    }

    @Test
    public void loginOkTest() throws Exception{
        Assert.assertTrue(loginScreen.testing(true, true, true));
    }

    @Test
    public void loginFailTest() throws Exception{
        Assert.assertFalse(loginScreen.testing(false,true, true));
    }
}
