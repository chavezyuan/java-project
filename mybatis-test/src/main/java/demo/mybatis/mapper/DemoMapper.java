package demo.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public interface DemoMapper {
	public int select(@Param("id")int id);
}
