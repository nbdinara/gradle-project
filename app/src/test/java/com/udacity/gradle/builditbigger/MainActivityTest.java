package com.udacity.gradle.builditbigger;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;
import com.udacity.gradle.builditbigger.services.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
@Config(manifest = Config.NONE)
public class MainActivityTest {

    Context context;

    @Mock
    MyApi myApiMock;

    @Mock
    MyApi.SayHi sayHiMock;


    @Test
    public void testVerifyJoke() throws InterruptedException, IOException {
        MockitoAnnotations.initMocks(this);

        assertTrue(true);

        MyBean myBean = new MyBean();
        myBean.setData("fakeJoke");
        when(sayHiMock.execute()).thenReturn(myBean);
        when(myApiMock.sayHi()).thenReturn(sayHiMock);

        final CountDownLatch latch = new CountDownLatch(1);
        context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(myApiMock) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue(result.length() > 0);
                latch.countDown();
            }
        };
        testTask.execute(context);
        latch.await();
    }
}