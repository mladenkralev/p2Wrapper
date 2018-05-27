package com.demo.ds.test.impl;

import com.demo.ds.test.TestService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class DemoDeclarativeServices implements TestService{

    @Activate
    public void start(BundleContext context) throws Exception {
//        for(int index = 0; index < 100; index++)
//            System.out.println("Working DS bundle");
    }

    @Deactivate
    public void stop(BundleContext context) throws Exception {
//        System.out.println("did not work");
    }

    @Override
    public void testSomething() {

    }
}