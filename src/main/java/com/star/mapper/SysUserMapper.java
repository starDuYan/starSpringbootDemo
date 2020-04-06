package com.star.mapper;


import com.star.bean.SysUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysUserMapper implements RowMapper<SysUser> {

	@Override
	public SysUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysUser user = new SysUser();
		user.setId(rs.getLong("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setCreateTime(rs.getTimestamp("create_time"));
		return user;
	}
}
