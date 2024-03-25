package Test_Suite;

import Setup_Browser.SetUP;
import Setup_Browser.login;
import org.testng.annotations.Test;

import java.io.IOException;

public class Start {
    @Test ( priority = 1 )
    void SetupBrowserTest() throws IOException, InterruptedException {
       SetUP loginTest = new SetUP();
        loginTest.setupBrowser();
    }

    @Test ( priority = 2 )
    void Logingdata() throws IOException, InterruptedException {
        login loginTest = new login();
        loginTest.loging();
    }





}


