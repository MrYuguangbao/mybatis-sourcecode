package com.example.mysql2es.mybatis.model.handler;

import com.example.mysql2es.mybatis.model.Enabled;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: admin
 * @Description: 自定义类型处理器
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.model.handler
 * @CreateTime: 2020-11-21 23:31:27
 */
public class EnabledTypeHandler implements TypeHandler<Enabled> {

    private final Map<Integer, Enabled> map = new HashMap<>();

    public EnabledTypeHandler(){
        for (Enabled value : Enabled.values()) {
            map.put(value.getValue(), value);
        }
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Enabled parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public Enabled getResult(ResultSet rs, String columnName) throws SQLException {
        Integer value = rs.getInt(columnName);
        return map.get(value);
    }

    @Override
    public Enabled getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = rs.getInt(columnIndex);
        return map.get(value);
    }

    @Override
    public Enabled getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = cs.getInt(columnIndex);
        return map.get(value);
    }
}
