package com.star.dao.impl;

import com.star.bean.Student;
import com.star.bean.SysUser;
import com.star.bean.Syslog;
import com.star.dao.SysUserDao;
import com.star.dao.SyslogDao;
import com.star.mapper.StudentMapper;
import com.star.mapper.SysUserMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.dao.impl
 * @date 2020/04/05 15:43
 */
@Log4j
@Repository
public class SysUserDaoImpl implements SysUserDao {

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Override
    public SysUser queryById(Long id) {
        String sql = "select * from sys_user where id=?";
        Object[] args = { id };
        int[] argTypes = { Types.BIGINT };
        List<SysUser> list = this.jdbcTemplate.query(sql, args, argTypes, new SysUserMapper());
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<SysUser> queryAll(SysUser sysUser) {
        return null;
    }

    @Override
    public int insert(SysUser sysUser) {
        String sql = "insert into sys_user(username,password,create_time) values(:username,:password,:createTime)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        return npjt.update(sql, new BeanPropertySqlParameterSource(sysUser));
    }

    @Override
    public int update(SysUser sysUser) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}