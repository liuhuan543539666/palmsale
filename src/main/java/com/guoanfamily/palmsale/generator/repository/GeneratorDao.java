package com.guoanfamily.palmsale.generator.repository;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成dao操作
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/9 9:43
 * @version: 1.0
 */
@Component
public class GeneratorDao {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * 查询表信息
     *
     * @param tableName
     * @return
     */
    public Map<String, String> findTableInfoByTableName(String tableName) {
        Query query = entityManager.createNativeQuery("select table_name as tableName, engine, table_comment as tableComment, create_time as createTime from information_schema.tables where table_schema = (select database()) and table_name = '" + tableName + "'");
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List rows = query.getResultList();
        if (rows != null) {
            return (Map<String, String>) rows.get(0);
        }
        return null;
    }

    /**
     * 查询列信息
     *
     * @param tableName
     * @return
     */
    public List<Map<String, String>> findTableColumnInfoByTableName(String tableName) {
        Query query = entityManager.createNativeQuery("select column_name AS columnName, data_type AS dataType, column_comment AS columnComment, column_key AS columnKey, extra from information_schema.columns where table_name = '" + tableName + "' and table_schema = (select database()) order by ordinal_position");
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List rows = query.getResultList();
        if (rows != null) {
            return rows;
        }
        return null;
    }
}