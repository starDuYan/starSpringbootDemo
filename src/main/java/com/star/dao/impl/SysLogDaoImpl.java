package com.star.dao.impl;

import com.star.bean.Syslog;
import com.star.dao.SyslogDao;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.dao.impl
 * @date 2020/04/05 15:43
 */
@Log4j
@Repository
public class SysLogDaoImpl implements SyslogDao {

    @Autowired
    @Qualifier("oracleJdbcTemplate")
    private JdbcTemplate oraclejdbcTemplate;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqljdbcTemplate;
    /**
    * @Description: oracle
    * @Param: 
    * @return: 
    * @Author: duxing
    * @Date: 2020/04/05
    */
    @Override
    public void saveSyslogOracle(Syslog syslog) {
        StringBuffer sql = new StringBuffer("insert into sys_log ");
        sql.append("(id,username,operation,time,method,params,ip,create_time) ");
        sql.append("values(seq_sys_log.nextval,:username,:operation,:time,:method,");
        sql.append(":params,:ip,:createTime)");

        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.oraclejdbcTemplate.getDataSource());
        npjt.update(sql.toString(), new BeanPropertySqlParameterSource(syslog));
        log.error("saveSyslogOracle:"+npjt);
    }
    /**
    * @Description: mysql
    * @Param: 
    * @return: 
    * @Author: duxing
    * @Date: 2020/04/05
    */
    @Override
    public void saveSyslogMysql(Syslog syslog) {
        StringBuffer sql = new StringBuffer("insert into sys_log ");
        sql.append("(username,operation,time,method,params,ip,create_time) ");
        sql.append("values(:username,:operation,:time,:method,");
        sql.append(":params,:ip,:createTime)");

        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.mysqljdbcTemplate.getDataSource());
        npjt.update(sql.toString(), new BeanPropertySqlParameterSource(syslog));
        log.error("saveSyslogMysql:"+npjt);
    }
}