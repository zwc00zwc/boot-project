package sofa.server.api.impl;

import sofa.server.api.TestServer;
import sofa.server.common.model.TestModel;

/**
 * @Author: zhengwenchao
 * @Date: 2019 2019-04-23 14:12
 */
public class TestServerImpl implements TestServer {
    @Override
    public TestModel test() {
        TestModel testModel = new TestModel();
        testModel.setId(1);
        testModel.setDesc("testmodel");
        return testModel;
    }
}
