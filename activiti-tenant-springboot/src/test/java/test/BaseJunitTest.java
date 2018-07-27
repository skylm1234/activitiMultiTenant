package test;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.multitenant.TenantInfoHolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.keruyun.workflow.WorkFlowApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkFlowApplication.class)

public abstract class BaseJunitTest {

  @Autowired
  protected ProcessEngine processEngine;

  @Autowired
  protected TenantInfoHolder tenantInfoHolder;
}
