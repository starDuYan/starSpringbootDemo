package com.star.dao.impl;

import com.star.bean.Student;
import com.star.dao.StudentMysqlDao;
import com.star.dao.StudentOracleDao;
import com.star.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.dao.impl
 * @date 2020/04/01 23:01
 */
@Repository("studentOracleDao")
public class StudentOracleDaoImpl implements StudentOracleDao {

    @Autowired
    @Qualifier("oracleJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Student student) {
        String sql = "insert into STUDENT_MYBATIS(USERNAME,PASSWORD,ICON,EMAIL,NICK_NAME,NOTE,CREATE_TIME,LOGIN_TIME,STATUS) values(:username,:password,:icon,:email,:nickName,:note,:createTime,:loginTime,:status)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        return npjt.update(sql, new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int update(Student student) {
        String sql = "update STUDENT_MYBATIS set USERNAME = ?,PASSWORD = ?,ICON = ?,EMAIL = ?,NICKNAME = ?,NOTE = ?,CREATETIME = ?,LOGINTIME = ?,STATUS = ? where ID = ?";
        Object[] args = { student.getUsername(), student.getPassword(), student.getIcon(),student.getEmail(),student.getNickName(),student.getNote(),student.getCreateTime(),student.getLoginTime(),student.getStatus() };
        int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "delete from STUDENT_MYBATIS where ID = ?";
        Object[] args = { id };
        int[] argTypes = { Types.BIGINT };
        int result = this.jdbcTemplate.update(sql, args, argTypes);
        return result;
    }

    @Override
    public List<Map<String, Object>> queryStudentsListMap() {
        String sql = "select * from STUDENT_MYBATIS";
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public Student queryStudentById(Long id) {
        String sql = "select * from STUDENT_MYBATIS where ID = ?";
        Object[] args = { id };
        int[] argTypes = { Types.BIGINT };
        List<Student> studentList = this.jdbcTemplate.query(sql, args, argTypes, new StudentMapper());
        if (studentList != null && studentList.size() > 0) {
            return studentList.get(0);
        } else {
            return null;
        }
    }
}