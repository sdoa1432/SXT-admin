package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface MerchantMapper {
    /**
     *
     * @param merchant_account 商户账号
     * @param merchant_password 商户密码
     * @return 实例对象
     */
    Merchant queryByAccountAndPassword(String merchant_account, String merchant_password);
    /**
     * 分页查询指定行数据
     *
     * @param merchant 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Merchant> queryAllByLimit(Merchant merchant, @Param("pageable") Pageable pageable);
    List<Merchant> queryAll(Merchant merchant);
    /**
     * 统计总行数
     *
     * @param merchant 查询条件
     * @return 总行数
     */
    long count(Merchant merchant);
    /**
     * 新增数据
     *
     * @param merchant 实例对象
     * @return 影响行数
     */
    int insert(Merchant merchant);
    /**
     * 批量新增数据
     *
     * @param entities List<MerchantPO> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Merchant> entities);
    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<MerchantPO> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Merchant> entities);
    /**
     * 更新数据
     *
     * @param merchant 实例对象
     * @return 影响行数
     */
    int update(Merchant merchant);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}
