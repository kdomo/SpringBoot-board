package com.kdomo.Myhome.repository;

import com.kdomo.Myhome.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,Long> {


}
