package jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.sun.jmx.snmp.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alan.zheng on 2017/4/18.
 */
public class TestJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);
    public void execute(ShardingContext shardingContext) {
        logger.info("运行TestJob");
        Date date = new Date();
        SimpleDateFormat sdfFrom = null;
        String sRet = null;
        try {
            sdfFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sRet = sdfFrom.format(date).toString();
        } catch (Exception ex) {
        } finally {
            sdfFrom = null;
        }
        System.out.print("运行TestJob,时间:" + sRet);
    }
}
