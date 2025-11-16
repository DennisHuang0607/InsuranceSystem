package com.project01.component;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PolicyNumberComponent {
	
	@PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String generateNewPolicyNumber() {
    	try {
    		String prefix = "PN";
            String datePart = LocalDate.now().toString().replace("-", "");

            //查詢當天最大編號
            String hql = "SELECT MAX(p.policyNumber) FROM Policy p WHERE p.policyNumber LIKE :prefixDate";
            String likePattern = prefix + datePart + "%";

            List<String> result = entityManager.createQuery(hql, String.class)
                                             .setParameter("prefixDate", likePattern)
                                             .getResultList();
            String lastNumber = result.isEmpty() ? null : result.get(0);
            int nextNumber = 1;
            
            if (lastNumber != null) {
                // 取出最後6位數字（流水號）
                String numberPart = lastNumber.substring(prefix.length() + datePart.length());
                nextNumber = Integer.parseInt(numberPart) + 1;
            }

            return String.format("%s%s%06d", prefix, datePart, nextNumber);
    	}
    	catch(Exception e) {
            throw new RuntimeException("無法產生新的 policyNumber", e);
    	}
    	
    	
    }
}
