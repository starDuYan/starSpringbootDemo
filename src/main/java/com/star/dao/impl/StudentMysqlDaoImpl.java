package com.star.dao.impl;

import com.star.bean.Student;
import com.star.dao.StudentMysqlDao;
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
@Repository("studentMysqlDao")
public class StudentMysqlDaoImpl implements StudentMysqlDao {

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Student student) {
        String sql = "insert into student_mybatis(username,password,icon,email,nick_name,note,create_time,login_time,status) values(:username,:password,:icon,:email,:nickName,:note,:createTime,:loginTime,:status)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        return npjt.update(sql, new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int update(Student student) {
        String sql = "update student_mybatis set username = ?,password = ?,icon = ?,email = ?,nickName = ?,note = ?,createTime = ?,loginTime = ?,status = ? where id = ?";
        Object[] args = { student.getUsername(), student.getPassword(), student.getIcon(),student.getEmail(),student.getNickName(),student.getNote(),student.getCreateTime(),student.getLoginTime(),student.getStatus() };
        int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "delete from student_mybatis where id = ?";
        Object[] args = { id };
        int[] argTypes = { Types.BIGINT };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> queryStudentsListMap() {
        String sql = "select * from student_mybatis";
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public Student queryStudentById(Long id) {
        String sql = "select * from student_mybatis where id = ?";
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