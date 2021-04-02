package org.apache.ibatis.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.demo.model.People;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author admin
 * @date 2020/3/26 18:46
 */
@Mapper
public interface PeopleMapper {
    List<People> queryPeople(@Param("param") String param, RowBounds rowBounds);

    List<People> selectAll();

    int insertPeople(People people);

    int deletePeople(@Param("id")long id);

    People countFirstName(@Param("mytable") String mytable);
}
