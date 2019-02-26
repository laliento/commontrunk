package com.laliento.commontrunk.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.LogginAttempts;
import com.laliento.commontrunk.entity.Usuario;

@Repository
public interface LogginAttemptsRepository extends JpaRepository<LogginAttempts, Integer>{

	public Long countLogginAttemptsByUsuarioAndTiempoBetween(Usuario user,Date start,Date end);
}
