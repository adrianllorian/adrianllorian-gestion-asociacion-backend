package inicio;


import java.security.NoSuchAlgorithmException;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@ComponentScan(basePackages= {"dao", "service", "util", "controller","seguridad"})
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages= {"dao"})
@SpringBootApplication
@EnableEurekaClient
public class AuthenticationApplication extends SpringBootServletInitializer{
	/*
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuthenticationApplication.class);
    }

*/
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
		
	}
	/*
	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/client.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/client.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("client");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}
	*/

	/*
	 @Bean
     public WebMvcConfigurer corsConfigurer() {
         return new WebMvcConfigurer() {
             @Override
             public void addCorsMappings(CorsRegistry registry) {
                 registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE", "OPTION");
             }
         };
     }
*/
	/*
	
		private String allPassword = "changeit";
	
		@Bean
		@LoadBalanced
		public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception{
			SSLContext sslContext = SSLContextBuilder
	                .create()
	                .loadKeyMaterial(ResourceUtils.getFile("classpath:fullchain.p12"), allPassword.toCharArray(), allPassword.toCharArray())
	                .loadTrustMaterial(ResourceUtils.getFile("classpath:fullchain.p12"), allPassword.toCharArray())
	                .build();

	        HttpClient client = HttpClients.custom()
	                .setSSLContext(sslContext)
	                .build();

	        return builder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client)).build();
		}
	 	
	*/
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/*
	@Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

	 private Connector createStandardConnector() {
	        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	        connector.setPort(3001);
	        return connector;
	    }
	    */
}
