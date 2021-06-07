package com.dit.himachal;

import com.dit.himachal.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
@EnableAsync
public class AppleSeasonIdCardHpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppleSeasonIdCardHpApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        return new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                Rfc6265CookieProcessor rfc6265CookieProcessor = new Rfc6265CookieProcessor();
//                rfc6265CookieProcessor.setSameSiteCookies("Strict");
//                context.setCookieProcessor(rfc6265CookieProcessor);
//            }
//        };
//    }

//    @Configuration
//    public class WebConfig implements WebMvcConfigurer {
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/**")
//                    .addResourceLocations("classpath:/static/","classpath:/images/")
//                    .setCachePeriod(0);
//        }
//    }


}
