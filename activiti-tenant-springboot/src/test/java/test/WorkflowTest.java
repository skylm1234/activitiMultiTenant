package test;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;
import com.keruyun.workflow.Constants;

/**
 * 多租户测试
 * 
 * @author lijianghuai
 *
 */
public class WorkflowTest extends BaseJunitTest {

  @Test
  public void testDeploy() {
    // 会在test1租户库中发布和启动流程
    tenantInfoHolder.setCurrentTenantId(Constants.TENANTINFO_A);
    Deployment deploy = processEngine.getRepositoryService().createDeployment()
        .addClasspathResource("process/process3.bpmn").name("流程3").deploy();
    Assert.assertNotNull(deploy);
    ProcessInstance processInstance =
        processEngine.getRuntimeService().startProcessInstanceByKey("process3");
    Assert.assertNotNull(processInstance);
    tenantInfoHolder.clearCurrentTenantId();

    // 会在test2租户库中发布和启动流程
    tenantInfoHolder.setCurrentTenantId(Constants.TENANTINFO_B);
    Deployment deploy2 = processEngine.getRepositoryService().createDeployment()
        .addClasspathResource("process/process3.bpmn").name("流程3").deploy();
    Assert.assertNotNull(deploy2);
    ProcessInstance processInstance2 =
        processEngine.getRuntimeService().startProcessInstanceByKey("process3");
    Assert.assertNotNull(processInstance2);
    tenantInfoHolder.clearCurrentTenantId();
  }
}
