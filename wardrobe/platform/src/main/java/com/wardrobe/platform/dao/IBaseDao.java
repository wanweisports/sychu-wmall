package com.wardrobe.platform.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao {

    boolean updateByJdbc(String sql, Object... params);

    Integer getAllCount(final String sql);

    Number getUniqueResult(final String sql);

    Integer getAllCount(final String sql, final Object... params);

    Integer getAllCount(final String sql, final Map<String, Object> params);

    Number getUniqueResult(final String sql, final Object... params);

    Object getUniqueObjectResult(final String sql, final Object... params);

    Number getUniqueResult(final String sql, final Map<String, Object> params);

    <T> T get(final Class<T> clazz, final Serializable id);

    <T> T getToEvict(final Class<T> clazz, final Serializable id);

    <T> T load(final Class<T> clazz, final Serializable id);

    void save(final Object obj, final Serializable id);

    boolean saveCatchException(final Object obj, final Serializable id);

    void delete(final Object obj);

    int delete(final String tableName, final String idName, final Integer id);

    <T> List<T> queryByHql(final String hql, final Integer pageNumber, final Integer pageSize, final Object... params);

    <T> List<T> queryByHql(final String hql, final Object... params);
    
    <T> List<T> queryByHql(final String hql, final Map<String, Object> map);

    <T> T queryByHqlFirst(final String hql, final Object... params);

    <T> T queryByHqlFirst(final String hql, final Map<String, Object> params);

    List<Map<String, Object>> queryBySql(String sql);

    Map<String, Object> queryBySqlFirst(final String sql, final Object... params);

    Map<String, Object> queryBySqlFirst(final String sql, final Map<String, Object> params);

    List<Map<String, Object>> queryBySql(final String sql, final Object... params);

    List<Map<String, Object>> queryBySql(final String sql, final Map<String, Object> params);

    List<Map<String, Object>> queryBySql(final String sql, final Integer pageNumber, final Integer pageSize, final Object... params);

    List<Map<String, Object>> queryBySql(final String sql, final Integer pageNumber, final Integer pageSize, final Map<String, Object> params);

    int updateBySql(final String sql);

    int updateBySql(final String sql, final Object... params);

    int updateBySql(final String sql, final Map<String, Object> params);

    int countRowNum(final String hql);

}
