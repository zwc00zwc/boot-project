package application.jobs;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;

import java.util.Date;

/**
 * Created by XR on 2016/12/12.
 */
public class TestJob extends AbstractSimpleElasticJob {
    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        try {
            Thread.sleep(1000);
            System.out.print("TestJob"+new Date().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
