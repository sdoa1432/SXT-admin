package com.tencent.wxcloudrun.dao;

import java.util.List;

import com.tencent.wxcloudrun.model.UserVoucher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

/**
 * 用户消费券领取表;(user_voucher)表数据库访问层
 * @author : http://www.yonsum.com
 * @date : 2025-10-21
 */
@Mapper
public interface UserVoucherMapper{
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserVoucher queryById(String id);
    /**
     * 分页查询指定行数据
     *
     * @param userVoucher 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UserVoucher> queryAllByLimit(UserVoucher userVoucher, @Param("pageable") Pageable pageable);
    /**
     * 查询指定数据
     *
     * @param userVoucher 查询条件
     * @return 对象列表
     */
    List<UserVoucher> queryAll(UserVoucher userVoucher);
    /**
     * 统计总行数
     *
     * @param userVoucher 查询条件
     * @return 总行数
     */
    long count(UserVoucher userVoucher);
    /**
     * 新增数据
     *
     * @param userVoucher 实例对象
     * @return 影响行数
     */
    int insert(UserVoucher userVoucher);
    /**
     * 批量新增数据
     *
     * @param entities List<UserVoucher> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserVoucher> entities);
    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<UserVoucher> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserVoucher> entities);
    /**
     * 更新数据
     *
     * @param userVoucher 实例对象
     * @return 影响行数
     */
    int update(UserVoucher userVoucher);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}
