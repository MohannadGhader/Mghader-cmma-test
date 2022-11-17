package com.path.bo.omni.common;

import java.util.List;

import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.TemporaryReservationVO;
import com.path.lib.common.exception.DAOException;

/**
 * 
 */
public final class OmniCommonMethods {
	
	OmniCommonPullDAO omniCommonPullDAO;
	OmniCommonPushDAO omniCommonPushDAO;

//	public String validateRecurringReservation (ReservationCO reservationCO) throws DAOException {
//		
//		omniCommonPushDAO.insertTempReservation(reservationCO);
//		List<ReservationListVO> list = reservationCO.getListDate();
//		//for(ReservationListVO e :list) {
//		for (int s= 0 ; s< list.size(); s++) {
//			ReservationCO tempres= new ReservationCO();
//			tempres.setDate(reservationCO.getListDate().get(s).getDate());
//			tempres.setTime(reservationCO.getListDate().get(s).getTime());
//			tempres.setClient_id(reservationCO.getClient_id());
//			TemporaryReservationVO re = omniCommonPullDAO.returnTemporaryReservation(tempres);
//			if(re == null) {
//				System.out.println("no fake reservation");
//			}
//		}
//		return "aya shi";
//	}
}
