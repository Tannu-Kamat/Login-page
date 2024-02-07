package com.example.CustomerService.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name="MultiRequestAsyncThread")
    public Executor getThreadPoolExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor(){

            @Override
            public void execute(Runnable task){
                log.info("task submitted {}",task.toString());
                super.execute(task);
            }

            protected void afterExecute(Runnable r, Throwable t){
                log.info("task completed {}",r.toString());
                super.afterExecute(r,t);
            }
        };

        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(64);
        executor.setThreadNamePrefix("RequestThread:- ");
        executor.initialize();
        return executor;
    }
}
