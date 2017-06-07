import com.example.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by wanquan on 2017/5/31.
 */
public class testMain {
    @Resource
    private UserService userService;
    @Test
    public void testMain() {
        System.out.println(userService.selectUserById(1));
    }
}
