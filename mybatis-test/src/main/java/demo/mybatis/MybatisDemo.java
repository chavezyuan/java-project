package demo.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import demo.mybatis.mapper.DemoMapper;

public class MybatisDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
//		  int batchId = session.selectOne("demo.mybatis.mapper.DemoMapper.select", 10);
			DemoMapper mapper = session.getMapper(DemoMapper.class);
			System.out.println(mapper.select(10));
		} finally {
		  session.close();
		}
	}

}
