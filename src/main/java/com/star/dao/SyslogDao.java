package com.star.dao;

import com.star.bean.Syslog;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.dao
 * @date 2020/04/05 15:42
 */
public interface SyslogDao {
    void saveSyslogOracle(Syslog syslog);
    void saveSyslogMysql(Syslog syslog);
}
    