package com.project01.component;

import java.io.Serializable;
import java.util.List;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

//自訂insurer_id產生器
public class InsurerIdGenerator implements IdentifierGenerator{

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            String prefix = "INS";

            String hql = "SELECT i.insurer_id FROM Insurer i ORDER BY i.insurerId DESC";
            List<String> result = session.createQuery(hql,String.class).setMaxResults(1).getResultList();

            String lastId = result.isEmpty() ? null : result.get(0);
            int nextId = 1;

            if (lastId != null) {
                nextId = Integer.parseInt(lastId.substring(prefix.length())) + 1;
            }

            return String.format("%s%06d", prefix, nextId);
        } 
        catch (Exception e) {
            throw new RuntimeException("無法產生新的 insurerId", e);
        }
    }
}
