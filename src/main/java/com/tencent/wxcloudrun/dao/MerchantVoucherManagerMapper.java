package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.MerchantVoucherManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface MerchantVoucherManagerMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MerchantVoucherManager queryById(String id);

    List<MerchantVoucherManager> queryByMerchantType(int type);
    /**
     * 分页查询指定行数据
     *
     * @param merchantVoucherManager 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<MerchantVoucherManager> queryAllByLimit(MerchantVoucherManager merchantVoucherManager, @Param("pageable") Pageable pageable);
    /**
     * 查询指定条件数据
     *
     * @param merchantVoucherManager 查询条件
     * @return 对象列表
     */
    List<MerchantVoucherManager> queryAll(MerchantVoucherManager merchantVoucherManager);
    /**
     * 统计总行数
     *
     * @param merchantVoucherManager 查询条件
     * @return 总行数
     */
    long count(MerchantVoucherManager merchantVoucherManager);
    /**
     * 新增数据
     *
     * @param merchantVoucherManager 实例对象
     * @return 影响行数
     */
    int insert(MerchantVoucherManager merchantVoucherManager);
    /**
     * 批量新增数据
     *
     * @param entities List<MerchantVoucherManagerPO> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MerchantVoucherManager> entities);
    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<MerchantVoucherManagerPO> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<MerchantVoucherManager> entities);
    /**
     * 更新数据
     *
     * @param merchantVoucherManager 实例对象
     * @return 影响行数
     */
    int update(MerchantVoucherManager merchantVoucherManager);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}
