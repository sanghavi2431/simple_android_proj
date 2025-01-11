package studios.codelight.smartloginlibrary;

import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    @Test
    public void testApp() {
        // Use ApplicationProvider to get the application context
        Context appContext = ApplicationProvider.getApplicationContext();

        // Test that the app context is not null
        assertNotNull(appContext);
    }
}