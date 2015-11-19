package com.cnlot.booking.service.impl;

import com.cnlot.booking.domain.Trainer;
import com.cnlot.booking.dao.TrainerMapper;
import com.cnlot.booking.service.TrainerService;
import com.github.xy8864.webGenerator.base.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("trainer")
public class TrainerServiceImpl extends BaseServiceImpl<Trainer> implements TrainerService{

	@Autowired TrainerMapper trainerMapper;

	public TrainerMapper getMapper(){
		return trainerMapper;
	}


}