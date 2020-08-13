import com.shiro.DemoApplication;
import com.shiro.entity.mysql.MUserTable;
import com.shiro.mapper.mysql.MUserTableMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.*;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class  uuidTest {

    @Autowired
    private MUserTableMapper mUserTableMapper;

    @Test
    public void test1(){
        Set<String> ss = mUserTableMapper.selectRoleByName("芳芳");
        System.out.println(ss);
    }

    @Test
    public void test2(){
        MUserTable s = mUserTableMapper.selectAllByUsername("芳芳");
        System.out.println(s);
    }

    @Test
    public void test3(){
        Set<String> sss = mUserTableMapper.selectPermissionByName("芳芳");
        System.out.println(sss);
    }

    @Test
    public void test4(){
        MUserTable mUserTable = new MUserTable();
        mUserTable.setUsername("lli");
        mUserTable.setPassword("123456");
        mUserTable.setRole_id(1);

        String ss=String.valueOf(mUserTable.getPassword());

        Object password = new SimpleHash("md5", ss,
                ByteSource.Util.bytes(mUserTable.getUsername()), 1024).toHex();
        System.out.println(password);
    }
}
