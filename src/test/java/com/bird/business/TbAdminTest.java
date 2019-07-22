package com.bird.business;

import com.bird.business.domain.TbAdmin;
import com.bird.business.domain.TbAdminExample;
import com.bird.business.service.ITbAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

/**
 * mybatis-generator相关方法的测试类
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:spring-mvc.xml","classpath:mybatis-config.xml","classpath:spring-mybatis.xml"})
public class TbAdminTest {

    @Autowired
    private ITbAdminService tbAdminService;

    @Test
    public void insertTest(){
        TbAdmin tbAdmin = new TbAdmin();
        tbAdmin.setId((long)33);
        tbAdmin.setUsername("cuihui");
        tbAdmin.setPassword("asdafa");
        tbAdmin.setFullname("asdasd");
        tbAdmin.setRoleId((long)1);
        //当有部分字段未设值时，使用insertSelective(其实不影响)
        //当有所有字段均已设值时，使用insert
        tbAdminService.insert(tbAdmin);
    }

    @Test
    public void selectByExampleTest(){
        TbAdminExample tbAdminExample = new TbAdminExample();
        //条件不满足可以新加
        tbAdminExample.or().andFullnameLike("%崔%").andUsernameEqualTo("cuihui");
        //想要搜索结果包含大字段类型(字段类型为longtext)，则必须使用selectByExampleWithBLOBs。
        // 无需检索大字段，则使用selectByExample
        List<TbAdmin> tbAdmins = tbAdminService.selectByExample(tbAdminExample);

        System.out.println(tbAdmins);
    }

    @Test
    public void updateByExampleTest(){
        TbAdmin tbAdmin = new TbAdmin();
        tbAdmin.setId((long)24);
        tbAdmin.setUsername("cuihui");
        tbAdmin.setPassword("123");
        tbAdmin.setFullname("123");
        tbAdmin.setRoleId((long)1);

        TbAdminExample tbAdminExample = new TbAdminExample();
        tbAdminExample.or().andIdEqualTo((long)1);



        //updateByExample　　　　　　　　如果example定义了两个字段，数据库共4个字段，则修改数据库的两个字段，其余两个字段改为null；
        //updateByExampleSelective　　　　如果example定义了两个字段，数据库共4个字段，则修改数据库的两个字段，其余两个字段不动；
        //updateByExampleWithBLOBs　　　和updateByExample相比此方法可以修改大字段类型，其余性质和updateByExample相同
        //updateByPrimaryKey　　　　　　　如果record定义了两个字段，其中有一个字段是主键，数据库共4个字段，则根据主键修改数据库的两个字段，其余两个字段改为null；
        //updateByPrimaryKeySelective　　　如果record定义了两个字段，其中有一个字段是主键，数据库共4个字段，则根据主键修改数据库的两个字段，其余两个字段不动；
        //updateByPrimaryKeyWithBLOBs　　和updateByPrimaryKey相比此方法可以修改大字段类型，其余性质和updateByPrimaryKey相同

        tbAdminService.updateByPrimaryKey(tbAdmin);

    }

    @Test
    public void selectByExampleForPageTest(){
        TbAdminExample tbAdminExample = new TbAdminExample();
        //条件不满足可以新加
//        tbAdminExample.or().andFullnameLike("%崔%").andUsernameEqualTo("cuihui");
        tbAdminExample.setOrderByClause("id");

        //设置分页
        PageHelper.startPage(2, 5);
        //获取分页数据
        List<TbAdmin> tbAdmins = tbAdminService.selectByExample(tbAdminExample);
        PageInfo<TbAdmin> pageInfo=new PageInfo<TbAdmin>(tbAdmins);
        System.out.println(pageInfo);
    }
}