package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.spring.annotation.AutoRefreshValue;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author liuyuyu
 */
@Slf4j
@Component
public class DictatorAutoRefresher implements ApplicationContextAware {
    private ReentrantLock lock = new ReentrantLock();
    private static ApplicationContext applicationContext;

    private AutowiredAnnotationBeanPostProcessor beanPostProcessor;

    @Scheduled(fixedRateString = "${dictator.refresh.rate:1000}")
    public void refresh() {
        this.lock.lock();
        try {
            applicationContext.getBeansWithAnnotation(AutoRefreshValue.class)
                    .forEach((key, bean) -> {
                        beanPostProcessor.processInjection(bean);
                        log.debug("refresh bean {}", bean);
                    });
            log.debug("refresh end.");
        } finally {
            this.lock.unlock();
        }
    }

    @PostConstruct
    public void init() {
        this.beanPostProcessor = applicationContext.getBean(AutowiredAnnotationBeanPostProcessor.class);
        log.info("refresher loaded.");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }
}