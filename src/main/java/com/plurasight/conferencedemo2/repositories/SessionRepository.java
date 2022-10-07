package com.plurasight.conferencedemo2.repositories;

import com.plurasight.conferencedemo2.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
