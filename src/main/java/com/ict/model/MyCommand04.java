package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class MyCommand04 implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		List<VO> list = DAO.getListAll();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (VO k : list) {
			sb.append("{");
			sb.append("\"m_idx\""+":"+"\""+k.getM_idx()+"\",");
			sb.append("\"m_id\""+":"+"\""+k.getM_id()+"\",");
			sb.append("\"m_pw\""+":"+"\""+k.getM_pw()+"\",");
			sb.append("\"m_name\""+":"+"\""+k.getM_name()+"\",");
			sb.append("\"m_reg\""+":"+"\""+k.getM_reg().substring(0, 10)+"\",");
			sb.append("\"m_addr\""+":"+"\""+k.getM_addr()+"\"");
			sb.append("},");
		}
		
		return sb.toString().substring(0, sb.toString().length()-2)+"}]";
	}
}
