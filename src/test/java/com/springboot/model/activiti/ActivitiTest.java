package com.springboot.model.activiti;

import com.springboot.model.util.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 邝明山
 * @Date 2020/6/7
 */
@SpringBootTest
public class ActivitiTest {
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime runtime;
    @Autowired
    private SecurityUtil securityUtil;

    //自动创表
    @Test
    public void testGenTable() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
    /**
     * 部署
     */
    @Test
    public void testDefinition(){

    }

    @Test
    public void testStartInstance(){

    }
}
