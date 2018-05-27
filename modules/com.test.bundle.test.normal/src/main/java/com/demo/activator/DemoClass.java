package com.demo.activator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.artifact.repository.ArtifactRepositoryManager;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepositoryFactory;
import org.eclipse.equinox.internal.p2.metadata.repository.MetadataRepositoryManager;
import org.eclipse.equinox.internal.p2.metadata.repository.SimpleMetadataRepositoryFactory;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;

import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Properties;

public class DemoClass implements BundleActivator {


    @Override
    public void start(BundleContext context) throws Exception {
        Properties prop = System.getProperties();
        System.out.println ("JVM Vendor : " + prop.getProperty("java.vendor") );

        IPublisherInfo info = createPublisherInfo();
        IPublisherAction[] actions = createActions();
        Publisher publisher = new Publisher(info);
        publisher.publish(actions, new NullProgressMonitor());

    }
    public static IPublisherInfo createPublisherInfo() throws ProvisionException, URISyntaxException {
        PublisherInfo result = new PublisherInfo();
        IMetadataRepository metadataRepository = new SimpleMetadataRepositoryFactory().create(
                new URI("file:/C:/Users/mladen/Desktop/testRepo"), "Sample Metadata Repository",
                MetadataRepositoryManager.TYPE_SIMPLE_REPOSITORY, Collections.EMPTY_MAP);

        // Create the artifact repository.  This will fail if a repository already exists here
        IArtifactRepository artifactRepository = new SimpleArtifactRepositoryFactory().create(
                new URI("file:/C:/Users/mladen/Desktop/testRepo"), "Sample Artifact Repository",
                ArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, Collections.EMPTY_MAP);

        result.setMetadataRepository(metadataRepository);
        result.setArtifactRepository(artifactRepository);
        result.setArtifactOptions(IPublisherInfo.A_PUBLISH | IPublisherInfo.A_INDEX);
        return  result;
    }

    public static IPublisherAction[] createActions() {
        IPublisherAction[] result = new IPublisherAction[1];
        File[] bundleLocations = new File[1];
        bundleLocations[0] =  new File("D:\\osgi_platform\\felix-client\\build\\libs\\osgi\\core-ext");
        BundlesAction bundlesAction = new BundlesAction(bundleLocations);
        result[0] = bundlesAction;
        return result;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
//        System.out.println("did not work");
    }



}