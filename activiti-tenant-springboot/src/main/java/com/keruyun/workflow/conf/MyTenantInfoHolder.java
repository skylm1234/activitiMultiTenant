package com.keruyun.workflow.conf;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.activiti.engine.impl.cfg.multitenant.TenantInfoHolder;

/**
 * 必须实现TenantInfoHolder，官方推荐是使用ThreadLocal来切换租户 具体可以按自己需要切换，比如消息中间件
 * 
 * @author lijianghuai
 *
 */
public class MyTenantInfoHolder implements TenantInfoHolder {

  private final ThreadLocal<String> currentTenantId = new ThreadLocal<String>();
  private final Set<String> tenantIds = new HashSet<>();

  public MyTenantInfoHolder(String... tenantids) {
    for (String tenantid : tenantids) {
      tenantIds.add(tenantid);
    }
  }

  @Override
  public Collection<String> getAllTenants() {
    return tenantIds;
  }

  @Override
  public void setCurrentTenantId(String tenantid) {
    currentTenantId.set(tenantid);
    tenantIds.add(tenantid);
  }

  @Override
  public String getCurrentTenantId() {
    return currentTenantId.get();
  }

  @Override
  public void clearCurrentTenantId() {
    currentTenantId.remove();
  }

}
