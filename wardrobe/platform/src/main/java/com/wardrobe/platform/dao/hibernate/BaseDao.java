package com.wardrobe.platform.dao.hibernate;

import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.dao.IBaseDao;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.*;

public class BaseDao extends HibernateDaoSupport implements IBaseDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean updateByJdbc(String sql, Object ...params){
        return jdbcTemplate.update(sql, params) > 0;
    }

    @Override
    public Integer getAllCount(final String sql){
        return super.getHibernateTemplate().execute((Session s) -> ((Number)s.createSQLQuery(sql).uniqueResult()).intValue());
    }

    @Override
    public Number getUniqueResult(final String sql){
        return super.getHibernateTemplate().execute((Session s) -> (Number)s.createSQLQuery(sql).uniqueResult());
    }

    @Override
    public Integer getAllCount(final String sql, final Object ...params){
        return super.getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, false, params);
            return ((Number)query.uniqueResult()).intValue();
        });
    }

    @Override
    public Integer getAllCount(final String sql, final Map<String, Object> params){
        return super.getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, false, params);
            return ((Number)query.uniqueResult()).intValue();
        });
    }

    @Override
    public Number getUniqueResult(final String sql, final Object ...params){
        return super.getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, false, params);
            return (Number) query.uniqueResult();
        });
    }

    @Override
    public Object getUniqueObjectResult(final String sql, final Object ...params){
        return super.getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, false, params);
            return query.uniqueResult();
        });
    }

    @Override
    public Number getUniqueResult(final String sql, final Map<String, Object> params){
        return super.getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, false, params);
            return (Number) query.uniqueResult();
        });
    }

    @Override
    public <T> T get(final Class<T> clazz, final Serializable id){
        if(id == null) return null;
        return clazz.cast(getHibernateTemplate().get(clazz, id));
    }

    @Override
    public <T> T getToEvict(final Class<T> clazz, final Serializable id){
        if(id == null) return null;
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        T obj = clazz.cast(hibernateTemplate.get(clazz, id));
        hibernateTemplate.evict(obj);
        return obj;
    }

    @Override
    public <T> T load(final Class<T> clazz, final Serializable id){
        return getHibernateTemplate().load(clazz, id);
    }

    @Override
    public void save(final Object obj, final Serializable id){
        getHibernateTemplate().execute((Session s) -> {
            s.clear();
            if (id == null) {
                s.save(obj);
            } else {
                s.merge(obj);
            }
            s.flush();
            return null;
        });
    }

    @Override
    public boolean saveCatchException(final Object obj, final Serializable id){
        try {
            return getHibernateTemplate().execute((Session s) -> {
                try {
                    s.clear();
                    if (id == null) {
                        s.save(obj);
                    } else {
                        s.merge(obj);
                    }
                    s.flush();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int updateBySql(final String sql) {
        return getHibernateTemplate().execute((Session s) -> {
            s.clear();
            Query query = s.createSQLQuery(sql);
            int count = query.executeUpdate();
            s.flush();
            return count;
        });
    }

    @Override
    public int updateBySql(final String sql, final Object ...params) {
        return getHibernateTemplate().execute((Session s) -> {
            s.clear();
            Query query = getSqlQuery(s, sql, false, params);
            int count = query.executeUpdate();
            s.flush();
            return count;
        });
    }

    @Override
    public int updateBySql(final String sql, final Map<String, Object> params) {
        return getHibernateTemplate().execute((Session s) -> {
            s.clear();
            Query query = getSqlQuery(s, sql, false, params);
            int count = query.executeUpdate();
            s.flush();
            return count;
        });
    }

    @Override
    public void delete(final Object obj) {
        getHibernateTemplate().execute((Session s) -> {
            s.clear();
            s.delete(obj);
            s.flush();
            return null;
        });
    }

    @Override
    public int delete(final String tableName, final String idName, final Integer id) {
        return getHibernateTemplate().execute((Session s) -> {
            String q = "delete from " + tableName + " where " + idName + " =:id";
            int count = s.createSQLQuery(q).setParameter("id", id).executeUpdate();
            s.flush();
            return count;
        });
    }

    @Override
    public <T> List<T> queryByHql(final String hql, final Integer pageNumber, final Integer pageSize, final Object ...params) {
       return getHibernateTemplate().execute((Session s) -> {
           Query query = getHqlQuery(s, hql, params);
           if (pageNumber != null && pageSize != null && pageSize > 0) {
               setQueryPage(query, pageNumber, pageSize);
           }
           List list = query.list();
           return list;
       });
    }

    @Override
    public <T> List<T> queryByHql(final String hql, final Object ...params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getHqlQuery(s, hql, params);
            List list = query.list();
            return list;
        });
    }
    
    @Override
    public <T> List<T> queryByHql(final String hql, final Map<String, Object> map) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getHqlQuery(s, hql, map);
            List list = query.list();
            return list;
        });
    }

    @Override
    public <T> T queryByHqlFirst(final String hql, final Object... params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getHqlQuery(s, hql, params);

            query.setFirstResult(0);
            query.setMaxResults(1);

            List<T> list = query.list();
            if(list != null && !list.isEmpty()){
                return list.get(0);
            }
            return null;
        });
    }

    @Override
    public <T> T queryByHqlFirst(final String hql, final Map<String, Object> params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getHqlQuery(s, hql, params);

            query.setFirstResult(0);
            query.setMaxResults(1);

            List<T> list = query.list();
            if(list != null && !list.isEmpty()){
                return list.get(0);
            }
            return null;
        });
    }

    @Override
    public List<Map<String, Object>> queryBySql(final String sql) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = s.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List result = query.list();
            return result;
        });
    }

    @Override
    public Map<String, Object> queryBySqlFirst(final String sql, final Object ...params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, true, params);
            return getResultMapFirst(query);
        });
    }

    @Override
    public Map<String, Object> queryBySqlFirst(final String sql, final Map<String, Object> params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, true, params);
            return getResultMapFirst(query);
        });
    }

    @Override
    public List<Map<String, Object>> queryBySql(final String sql, final Object ...params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, true, params);
            List result = query.list();
            return result;
        });
    }

    @Override
    public List<Map<String, Object>> queryBySql(final String sql, final Map<String, Object> params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, true, params);
            List result = query.list();
            return result;
        });
    }

    @Override
    public List<Map<String, Object>> queryBySql(final String sql, final Integer pageNumber, final Integer pageSize, final Object ...params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, true, params);
            if(pageNumber != null && pageSize != null && pageSize > 0){
                setQueryPage(query, pageNumber, pageSize);
            }
            List result = query.list();
            return result;
        });
    }

    @Override
    public List<Map<String, Object>> queryBySql(final String sql, final Integer pageNumber, final Integer pageSize, final Map<String, Object> params) {
        return getHibernateTemplate().execute((Session s) -> {
            Query query = getSqlQuery(s, sql, true, params);
            if(pageNumber != null && pageSize != null && pageSize > 0){
                setQueryPage(query, pageNumber, pageSize);
            }
            List result = query.list();
            return result;
        });
    }

    private void setQueryPage(Query query, Integer pageNumber, Integer pageSize) {
        query.setFirstResult(((pageNumber == 0 ? 1 : pageNumber) - 1) * pageSize);
        query.setMaxResults(pageSize);
    }

    private Map<String, Object> getResultMapFirst(Query query) {
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<Map<String, Object>> result = query.list();
        if(result != null && result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    private Query getSqlQuery(Session s, String sql, boolean isMap, Object ...params) {
        Query query = s.createSQLQuery(sql);
        if(isMap)
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if(params != null){
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query;
    }

    private Query getSqlQuery(Session s, String sql, boolean isMap, Map<String, Object> params) {
        Query query = s.createSQLQuery(sql);
        setQueryValue(isMap, params, query);
        return query;
    }

    private Query getHqlQuery(Session s, String hql, Map<String, Object> params) {
        Query query = s.createQuery(hql);
        setQueryValue(false, params, query);
        return query;
    }

    private void setQueryValue(boolean isMap, Map<String, Object> params, Query query) {
        if(isMap)
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if(params != null) {
            Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
            List l = Arrays.asList(query.getNamedParameters());
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (l.contains(entry.getKey())) {
                    Object value = entry.getValue();
                    if (StrUtil.isNotBlank(StrUtil.objToStr(value))) {
                        if(value instanceof Collection<?>){
                            query.setParameterList(entry.getKey(), (Collection<?>)value);
                        }else if(value instanceof Object[]){
                            query.setParameterList(entry.getKey(), (Object[])value);
                        }else{
                        	query.setParameter(entry.getKey(), value);
                        }
                    }
                }
            }
        }
    }

    private Query getHqlQuery(Session s, String hql, Object ...params) {
        Query query = s.createQuery(hql);
        if(params != null){
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query;
    }

    @Override
    public int countRowNum(final String hql) {
        return (int) ((Long) super.getHibernateTemplate().iterate(hql).next()).longValue();
    }

}