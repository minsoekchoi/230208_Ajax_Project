package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class MyCommand05 implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		List<VO> list = DAO.getListAll();
		StringBuffer sb = new StringBuffer();
		for (VO k : list) {
			sb.append(k.getM_idx()+", ");
			sb.append(k.getM_id()+", ");
			sb.append(k.getM_pw()+", ");
			sb.append(k.getM_name()+", ");
			sb.append(k.getM_reg().substring(0, 10)+", ");
			sb.append(k.getM_addr()+"/");
			
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
}
