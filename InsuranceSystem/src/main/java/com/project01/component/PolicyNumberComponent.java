package com.project01.component;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PolicyNumberComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyNumberComponent.class);
	
	@PersistenceContext
    private EntityManager entityManager;

    public String generateNewPolicyNumber() {
    	try {
    		String prefix = "PN";
            String datePart = LocalDate.now().toString().replace("-", "");

            //查詢當天最大編號
//            String hql = "SELECT MAX(p.policyNumber) FROM Policy p WHERE p.policyNumber LIKE :prefixDate";
//            String likePattern = prefix + datePart + "%";

//            List<String> result = entityManager.createQuery(hql,String.class)
//                                             .setParameter("prefixDate",likePattern)
//                                             .getResultList();
//            String lastNumber = result.isEmpty() ? null : result.get(0);
//            int nextNumber = 1;
//            
//            if (lastNumber != null) {
//                //取出最後6位數字（流水號）
//                String numberPart = lastNumber.substring(prefix.length() + datePart.length());
//                nextNumber = Integer.parseInt(numberPart) + 1;
//            }
            
            //直接向MSSQL獲取Sequence的下一個值，解決高併發問題
            String sql = "SELECT NEXT VALUE FOR seq_PolicyNumber";
            Object result = entityManager.createNativeQuery(sql).getSingleResult();

            long nextVal = ((Number) result).longValue();
            String newPolicyNumber = String.format("%s%s%06d",prefix,datePart,nextVal);
            logger.info("已產生新的policyNumber:{}",newPolicyNumber);
            return newPolicyNumber;
    	}
    	catch(Exception e) {
    		logger.error("無法產生新的policyNumber，系統可能有非預期錯誤:",e);
            throw new RuntimeException("無法產生新的policyNumber",e);
    	}
    	
    	
    }
}
