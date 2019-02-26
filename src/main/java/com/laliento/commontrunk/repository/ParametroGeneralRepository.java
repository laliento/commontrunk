package com.laliento.commontrunk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.ParametroGeneral;

@Repository
public interface ParametroGeneralRepository extends JpaRepository<ParametroGeneral, String> {

	public ParametroGeneral findByClave(String clave);
}
