package com._520.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private MybatisUtil(){}
    static SqlSessionFactory factory;
    static {
        InputStream in = null;
        try {
            //读取资源文件
            in = Resources.getResourceAsStream("mybatis-cofig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取SqlSessionFactory对象，用来创建SQLSession对象
        factory = new SqlSessionFactoryBuilder().build(in);
    }
    public static SqlSession getSession(){//
        //参数表示是否手动提交事务，默认false
        return factory.openSession();
    }
    public static <T>T getMapper(Class <T> classes){//
        return getSession().getMapper(classes);
    }
}
