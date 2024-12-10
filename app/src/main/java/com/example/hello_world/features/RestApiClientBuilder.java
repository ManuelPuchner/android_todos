package com.example.hello_world.features;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.URLConnectionClientEngineBuilder;
import org.jboss.resteasy.client.jaxrs.internal.LocalResteasyProviderFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import jakarta.ws.rs.client.ClientBuilder;

/** Build a RestEasy Client with reduced dependencies on a lot of the standard Java Runtime classes so that it works even on the Android Java Runtime.
 * @author Christian Aberger (http://www.aberger.at)
 */
@Singleton
public class RestApiClientBuilder {
    final public ScheduledExecutorService scheduledExecutorService;
    final public ExecutorService executorService;

    @Inject
    public RestApiClientBuilder() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        executorService = Executors.newSingleThreadExecutor();
    }
    /** Build a reasteasy client
     * @see <a href="https://github.com/resteasy/resteasy>RESTEasy</a>
     */
    public <T> T build(Class <? extends T> type, String url) {
        ResteasyProviderFactory.setRegisterBuiltinByDefault(false);
        var factory = ResteasyProviderFactory.getInstance();
        factory.registerProvider(JsonMessageBodyReader.class, true);
        factory.registerProvider(JsonMessageBodyWriter.class, true);
        var builder = (ResteasyClientBuilder) ClientBuilder.newBuilder();
        builder.scheduledExecutorService(scheduledExecutorService);
        builder.executorService(executorService, false);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.providerFactory(new LocalResteasyProviderFactory(factory));
        var httpEngine = new URLConnectionClientEngineBuilder().resteasyClientBuilder(builder).build();
        builder.httpEngine(httpEngine);
        var client = builder.build();
        return client.target(url).proxy(type);
    }
}
