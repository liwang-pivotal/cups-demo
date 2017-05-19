package io.pivotal;


import java.net.URI;
import java.util.List;
import java.util.Properties;

import io.pivotal.auth.EnvParser;
import io.pivotal.domain.Customer;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class DemoConfig {
	
    
	@Bean(name = "gemfireCache")
    public ClientCache getGemfireClientCache() throws Exception {		
		
		Properties props = new Properties();
		props.setProperty("security-client-auth-init", "io.pivotal.auth.ClientAuthInitialize.create");
		ClientCacheFactory ccf = new ClientCacheFactory(props);
		List<URI> locatorList = EnvParser.getInstance().getLocators();
		for (URI locator : locatorList) {
			ccf.addPoolLocator(locator.getHost(), locator.getPort());
		}
		ccf.setPdxSerializer(new ReflectionBasedAutoSerializer(".*"));
		ccf.setPdxReadSerialized(false);
		ccf.setPoolSubscriptionEnabled(true);
		
		return ccf.create();
    }


	@Bean(name = "customer")
	public Region<String, Customer> customerRegion(@Autowired ClientCache clientCache) {
		ClientRegionFactory<String, Customer> customerRegionFactory = clientCache.createClientRegionFactory(ClientRegionShortcut.PROXY);

		Region<String, Customer> customerRegion = customerRegionFactory.create("customer");

		return customerRegion;
	}

}
