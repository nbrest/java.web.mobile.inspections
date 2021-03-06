package com.nicobrest.kamehouse.admin.config;

import com.nicobrest.kamehouse.admin.model.scheduler.job.ShutdownJob;
import com.nicobrest.kamehouse.admin.model.scheduler.job.SuspendJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Configuration class to setup the scheduler beans for the admin package.
 * 
 * @author nbrest
 *
 */
@Configuration
public class AdminSchedulerConfig {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private Scheduler scheduler;

  /**
   * Init AdminSchedulerConfig.
   */
  @PostConstruct
  public void init() {
    logger.info("init AdminSchedulerConfig");
    try {
      scheduler.addJob(shutdownJobDetail(), true);
      scheduler.addJob(suspendJobDetail(), true);
    } catch (SchedulerException e) {
      logger.error("Error adding admin jobs to the scheduler", e);
    }
  }

  /**
   * shutdownJobDetail bean.
   */
  @Bean(name = "shutdownJobDetail")
  public JobDetail shutdownJobDetail() {
    logger.info("Setting up shutdownJobDetail");
    return JobBuilder.newJob()
        .ofType(ShutdownJob.class)
        .storeDurably()
        .withIdentity(JobKey.jobKey("shutdownJobDetail"))
        .withDescription("Shutdown the server")
        .build();
  }

  /**
   * suspendJobDetail bean.
   */
  @Bean(name = "suspendJobDetail")
  public JobDetail suspendJobDetail() {
    logger.info("Setting up suspendJobDetail");
    return JobBuilder.newJob()
        .ofType(SuspendJob.class)
        .storeDurably()
        .withIdentity(JobKey.jobKey("suspendJobDetail"))
        .withDescription("Suspend the server")
        .build();
  }
}