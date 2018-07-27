package com.keruyun.workflow.conf;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.multitenant.TenantInfoHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.keruyun.workflow.Constants;

/**
 * 流程引擎相关配置
 * 
 * @author lijianghuai
 *
 */
@Configuration
public class ProcessEngineConf {

  @Resource(name = "primaryDataSource")
  private DataSource primaryDataSource;

  @Resource(name = "secondaryDataSource")
  private DataSource secondaryDataSource;

  /**
   * 多租户配置
   * 
   * @param tenantInfoHolder
   * @return
   */
  @Bean
  public MultiSchemaMultiTenantProcessEngineConfiguration processEngineConfiguration(
      TenantInfoHolder tenantInfoHolder) {
    MultiSchemaMultiTenantProcessEngineConfiguration conf =
        new MultiSchemaMultiTenantProcessEngineConfiguration(tenantInfoHolder);
    conf.setDatabaseSchemaUpdate(
        MultiSchemaMultiTenantProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    conf.setDatabaseType(MultiSchemaMultiTenantProcessEngineConfiguration.DATABASE_TYPE_MYSQL);
    conf.setLabelFontName("宋体");
    conf.setActivityFontName("宋体");
    // 下面是一些异步的东西
    // conf.setAsyncExecutorEnabled(true);
    // conf.setAsyncExecutorActivate(true);
    // conf.setAsyncExecutor(new SharedExecutorServiceAsyncExecutor(tenantInfoHolder));
    conf.registerTenant(Constants.TENANTINFO_A, primaryDataSource);
    conf.registerTenant(Constants.TENANTINFO_B, secondaryDataSource);
    return conf;
  }

  @Bean
  public ProcessEngine processEngine(ProcessEngineConfiguration conf) {
    return conf.buildProcessEngine();
  }

  /**
   * 租户
   * 
   * @return
   */
  @Bean
  public TenantInfoHolder tenantInfoHolder() {
    TenantInfoHolder tenantInfoHolder =
        new MyTenantInfoHolder(Constants.TENANTINFO_A, Constants.TENANTINFO_B);
    return tenantInfoHolder;
  }

  // 常用的一些service
  @Bean
  public RepositoryService getRepositoryService(ProcessEngine processEngine) {
    return processEngine.getRepositoryService();
  }

  @Bean
  public RuntimeService getRuntimeService(ProcessEngine processEngine) {
    return processEngine.getRuntimeService();
  }

  @Bean
  public TaskService getTaskService(ProcessEngine processEngine) {
    return processEngine.getTaskService();
  }

  @Bean
  public HistoryService getHistoryService(ProcessEngine processEngine) {
    return processEngine.getHistoryService();
  }

  @Bean
  public ManagementService getManagementService(ProcessEngine processEngine) {
    return processEngine.getManagementService();
  }

  @Bean
  public IdentityService getIdentityService(ProcessEngine processEngine) {
    return processEngine.getIdentityService();
  }

  @Bean
  public FormService getFormService(ProcessEngine processEngine) {
    return processEngine.getFormService();
  }
}
