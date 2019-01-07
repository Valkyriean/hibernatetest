package test;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import table.StudentsEntity;


import java.sql.Date;

/**
 * 这里是测试类
 * Created by mycomputer on 2017/4/15.
 */
public class StudentsTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    @Before
    public void init() {
        //创建配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        //  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();（使用这种方法会报错，unkonw Entity 。。。。）
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂对象

        sessionFactory = config.buildSessionFactory(serviceRegistry);
        //创建会话对象
        session = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();
    }


    @Test
    public void testSaveStudents() {

        StudentsEntity s = new StudentsEntity();
        s.setSid(2);
        s.setSname("张三丰");
        s.setGender("男");
        s.setBirthday(new Date(1));
        s.setAddress("大昌南路18号");
        session.save(s);//保存对象进入数据库
    }

    @After
    public void destory() {
        //提交事务
        transaction.commit();
        //关闭session
        session.close();
        //关闭sessionFactory
        sessionFactory.close();
    }
}
