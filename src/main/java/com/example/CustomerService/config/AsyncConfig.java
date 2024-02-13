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
public void execute(Runnable task) {
    long threadId = Thread.currentThread().getId();
    log.info("Task submitted: {} Thread ID: {}", task.toString(), threadId);
    super.execute(task);
}


            protected void afterExecute(Runnable r, Throwable t){
                long threadId = Thread.currentThread().getId();
                log.info("task completed {} Thread ID: {}",r.toString(),threadId);
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
