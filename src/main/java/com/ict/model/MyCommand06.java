package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;

public class MyCommand06 implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String m_id = request.getParameter("m_id");
		//System.out.println(m_id);
		String result = DAO.getIdChk(m_id);
		return result;
	}
}
