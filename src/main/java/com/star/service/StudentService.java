package com.star.service;

import com.star.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
	int add(Student student);
    int update(Student student);
    int deleteById(Long id);
    List<Map<String, Object>> queryStudentListMap();
    Student queryStudentById(Long id);
}
