package com.callor.diet.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.diet.config.DBContract;
import com.callor.diet.config.DBInfo;
import com.callor.diet.model.FoodDTO;
import com.callor.diet.model.FoodVO;
import com.callor.diet.service.FoodService;

public class FoodServiceImplV1 implements FoodService {

	protected Connection dbConn;

	public FoodServiceImplV1() {
		dbConn = DBContract.getdbConnection();
	}

	// DBMS에 SQL을 보내고
	// 결과를 받아서 List 데이터로 만들어 주는 함수
	protected List<FoodDTO> select(PreparedStatement pStr) throws SQLException {

		ResultSet rSet = pStr.executeQuery();
		List<FoodDTO> fList = new ArrayList<FoodDTO>();

		// DBMS에서 받은 데이터가 있으면
		while (rSet.next()) {

			FoodDTO fDTO = new FoodDTO();
			fDTO.setFd_code(rSet.getString(DBInfo.FOOD.fd_code));
			fDTO.setFd_name(rSet.getString(DBInfo.FOOD.fd_name));
			fDTO.setFd_year(rSet.getString(DBInfo.FOOD.fd_year));
			fDTO.setFd_ccode(rSet.getString(DBInfo.FOOD.fd_ccode));
			fDTO.setFd_icode(rSet.getString(DBInfo.FOOD.fd_icode));
			fDTO.setFd_once(rSet.getFloat(DBInfo.FOOD.fd_once));
			fDTO.setFd_capa(rSet.getFloat(DBInfo.FOOD.fd_capa));
			fDTO.setFd_kcal(rSet.getFloat(DBInfo.FOOD.fd_kcal));
			fDTO.setFd_protein(rSet.getFloat(DBInfo.FOOD.fd_protein));
			fDTO.setFd_fat(rSet.getFloat(DBInfo.FOOD.fd_fat));
			fDTO.setFd_carbo(rSet.getFloat(DBInfo.FOOD.fd_carbo));
			fDTO.setFd_sugar(rSet.getFloat(DBInfo.FOOD.fd_sugar));
			fDTO.setCp_name(rSet.getString(DBInfo.FOOD.cp_name));
			fDTO.setCp_cea(rSet.getString(DBInfo.FOOD.cp_cea));
			fDTO.setCp_tel(rSet.getString(DBInfo.FOOD.cp_tel));
			fDTO.setCp_addr(rSet.getString(DBInfo.FOOD.cp_addr));
			fDTO.setCp_item(rSet.getString(DBInfo.FOOD.cp_item));
			fDTO.setIt_name(rSet.getString(DBInfo.FOOD.it_name));
			
			fList.add(fDTO);
		}
		return fList;
	}

	@Override
	public List<FoodDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodDTO findById(String fd_code) {
		// TODO 식품코드로 입력하기
		String sql = " SELECT * FROM view_식품정보 ";
		sql += " WHERE 식품코드 = ? ";
		
		PreparedStatement pStr = null ;
		
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, fd_code);
			List<FoodDTO> fList = this.select(pStr);
			if(fList != null && fList.size() > 0) {
				return fList.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<FoodDTO> findByFName(String fd_name) {
		// TODO 식품명으로 검색하기
		
		String sql = " SELECT * FROM view_식품정보 ";
		sql += " WHERE 식품명 LIKE '%' || ? || '%' ";
		
		PreparedStatement pStr = null ; 
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, fd_name.trim());
			List<FoodDTO> fList = this.select(pStr);
			pStr.close();
			return fList ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public Integer insert(FoodVO foodVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(FoodVO foodVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(String fd_code) {
		// TODO Auto-generated method stub
		return null;
	}

}
