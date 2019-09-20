import com.wby.rocketmq.MQProducer;
import com.wby.rocketmq.RocketMQApplication;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wubinyu
 * @date 2019/9/20 14:55.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RocketMQApplication.class})
public class TestRocketMQ {

    @Resource
    private MQProducer mqProducer;

    @Test
    public void testProducer() {
        for (int i = 0; i < 10; i++) {
            mqProducer.sendMessage("Hello RocketMQ " + i, "TopicTest", "TagTest", "Key" + i);
        }
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
