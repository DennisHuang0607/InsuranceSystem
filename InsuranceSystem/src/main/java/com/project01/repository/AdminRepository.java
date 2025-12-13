package com.project01.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project01.entity.Admin;

@Repository
public class AdminRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增管理員
	public int register(Admin admin) {
		String sql = "INSERT INTO Admin(name,account_id,password,email) VALUES (?,?,?,?)";
		int row = jdbcTemplate.update(sql, (ps)->{
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getAccountId());
			ps.setString(3, admin.getPassword());
			ps.setString(4, admin.getEmail());
		});
		System.out.println("新增管理員" + admin.getAccountId());
		return row;
	}
	
	//查詢所有管理員
	public List<Admin> findAll() {
		String sql = "SELECT name,account_id,password,email FROM Admin";
		List<Admin> result = new ArrayList<>();
		result = jdbcTemplate.query(sql, (rs,num)->{
			Admin admin = new Admin();
			admin.setName(rs.getString("name"));
			admin.setAccountId(rs.getString("account_id"));
			admin.setPassword(rs.getString("password"));
			admin.setEmail(rs.getString("email"));
			return admin;
		});
		System.out.println("查詢管理員資料成功");
		return result;
	}
	
	//查詢單一管理員
	public Optional<Admin> findById(String account) {
		String sql = "SELECT name,account_id,password,email FROM Admin WHERE account_id = ?";
		try {
			Admin admin = jdbcTemplate.queryForObject(sql,new Object[]{account},(rs,num)->{
				Admin a = new Admin();
				a.setName(rs.getString("name"));
				a.setAccountId(rs.getString("account_id"));
				a.setPassword(rs.getString("password"));
				a.setEmail(rs.getString("email"));
				return a;
			});
			System.out.println("查詢管理員資料成功");
			return Optional.of(admin);
		}
		catch (EmptyResultDataAccessException e) {
			System.out.println("查無此管理員資料");
            return Optional.empty();
        }
		
	}
	
	//更新管理員資料
	public int update(Admin admin) {
		String sql = "UPDATE Admin SET name=?,password=?,email=? WHERE account_id=?";
		int row = jdbcTemplate.update(sql,(ps)->{
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getEmail());
			ps.setString(4, admin.getAccountId());
		});
		System.out.println("修改資料成功");
		return row;
	}
	
	//刪除管理員
	public int deleteById(String account) {
		String sql = "DElETE Admin WHERE account_id=?";
		int row = jdbcTemplate.update(sql,(ps)->{
			ps.setString(1, account);
		});
		System.out.println("帳號:" + account + " 刪除成功");
		return row;
	}
	
	//更新管理員密碼
	public int updatePassword(String account,String password) {
		String sql = "UPDATE Admin SET password=? WHERE account_id=?";
		int row = jdbcTemplate.update(sql,(ps)->{
			ps.setString(1, password);
			ps.setString(2, account);
		});
		System.out.println("修改密碼成功");
		return row;
	}
	
}
