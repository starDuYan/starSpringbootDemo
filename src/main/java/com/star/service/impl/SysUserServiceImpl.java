package com.star.service.impl;

import com.star.bean.SysUser;
import com.star.dao.SysUserDao;
import com.star.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-04-06 20:07:02
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long id) {
        return this.sysUserDao.queryById(id);
    }
    /**
     * 通过username查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */

    @Override
    public SysUser queryByName(String username) {
        return this.sysUserDao.queryByName(username);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDao.deleteById(id) > 0;
    }
}