package com.star.mapper;


import com.star.bean.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(rs.getLong("id"));
		student.setUsername(rs.getString("username"));
		student.setPassword(rs.getString("password"));
		student.setIcon(rs.getString("icon"));
		student.setEmail(rs.getString("email"));
		student.setNickName(rs.getString("nick_name"));
		student.setNote(rs.getString("note"));
		student.setCreateTime(rs.getTimestamp("create_time"));
		student.setLoginTime(rs.getTimestamp("login_time"));
		student.setStatus(rs.getInt("status"));
		return student;
	}
}
