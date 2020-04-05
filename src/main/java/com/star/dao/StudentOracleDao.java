package com.star.dao;

import com.star.bean.Student;

import java.util.List;
import java.util.Map;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.dao.impl
 * @date 2020/04/01 23:01
 */
public interface StudentOracleDao {
    int add(Student student);
    int update(Student student);
    int deleteById(Long id);
    List<Map<String,Object>> queryStudentsListMap();
    Student queryStudentById(Long id);
}
    