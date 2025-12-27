package com.project01.component;

import java.io.Serializable;
import java.util.List;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//自訂insurer_id產生器
public class InsurerIdGenerator implements IdentifierGenerator{
	
	private static final Logger logger = LoggerFactory.getLogger(InsurerIdGenerator.class);

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            String prefix = "INS";

//            String hql = "SELECT i.insurerId FROM Insurer i ORDER BY i.insurerId DESC";
//            List<String> result = session.createQuery(hql,String.class).setMaxResults(1).getResultList();
//
//            String lastId = result.isEmpty() ? null : result.get(0);
//            int nextId = 1;
//
//            if (lastId != null) {
//            	if (lastId.startsWith(prefix)) {
//                    try {
//                    	nextId = Integer.parseInt(lastId.substring(prefix.length())) + 1;
//                    }
//                    catch (NumberFormatException e) {
//                        logger.error("資料庫中發現格式錯誤的ID:{}，將重置ID計數或是請管理員檢查",lastId);
//                        throw new RuntimeException("ID格式解析失敗:" + lastId);
//                    }
//                }
//            }
            
            String sql = "SELECT NEXT VALUE FOR seq_InsurerId";
            Object result = session.createNativeQuery(sql).getSingleResult();
            
            long nextVal = ((Number) result).longValue();
            String newId = String.format("%s%06d",prefix,nextVal);
            logger.info("已產生新的insurerId:{}",newId);
            return newId;
        } 
        catch (Exception e) {
        	logger.error("無法產生新的insurerId，系統可能有非預期錯誤:",e);
            throw new RuntimeException("無法產生新的insurerId",e);
        }
    }
}
