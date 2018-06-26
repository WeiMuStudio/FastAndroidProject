package org.luyinbros.repository;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class RepositoryScopeTest {

    @Test
    public void instance() {
        Context appContext = InstrumentationRegistry.getTargetContext();
    }
}
