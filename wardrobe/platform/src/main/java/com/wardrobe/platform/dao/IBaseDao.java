package com.wardrobe.platform.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao {

    public boolean updateByJdbc(String sql, Object... params);

    public Integer getAllCount(final String sql);

    public Number getUniqueResult(final String sql);

    public Integer getAllCount(final String sql, final Object... params);

    public Integer getAllCount(final String sql, final Map<String, Object> params);

    public Number getUniqueResult(final String sql, final Object... params);

    public Object getUniqueObjectResult(final String sql, final Object... params);

    public Number getUniqueResult(final String sql, final Map<String, Object> params);

    public <T> T get(final Class<T> clazz, final Serializable id);

    public <T> T getToEvict(final Class<T> clazz, final Serializable id);

    public <T> T load(final Class<T> clazz, final Serializable id);

    public void save(final Object obj, final Serializable id);

    public boolean saveCatchException(final Object obj, final Serializable id);

    public void delete(final Object obj);

    public int delete(final String tableName, final String idName, final Integer id);

    public <T> List<T> queryByHql(final String hql, final Integer pageNumber, final Integer pageSize, final Object... params);

    public <T> List<T> queryByHql(final String hql, final Object... params);
    
    public <T> List<T> queryByHql(final String hql, final Map<String, Object> map);

    public <T> T queryByHqlFirst(final String hql, final Object... params);

    public <T> T queryByHqlFirst(final String hql, final Map<String, Object> params);

    public List<Map<String, Object>> queryBySql(String sql);

    public Map<String, Object> queryBySqlFirst(final String sql, final Object... params);

    public Map<String, Object> queryBySqlFirst(final String sql, final Map<String, Object> params);

    public List<Map<String, Object>> queryBySql(final String sql, final Object... params);

    public List<Map<String, Object>> queryBySql(final String sql, final Map<String, Object> params);

    public List<Map<String, Object>> queryBySql(final String sql, final Integer pageNumber, final Integer pageSize, final Object... params);

    public List<Map<String, Object>> queryBySql(final String sql, final Integer pageNumber, final Integer pageSize, final Map<String, Object> params);

    public List<Object> queryByHqlToOne(final String hql, final Object... params);

    public int updateBySql(final String sql);

    public int updateBySql(final String sql, final Object... params);

    public int updateBySql(final String sql, final Map<String, Object> params);

    public int getRowCount(final String hql);

    public int countRowNum(final String hql);

}
