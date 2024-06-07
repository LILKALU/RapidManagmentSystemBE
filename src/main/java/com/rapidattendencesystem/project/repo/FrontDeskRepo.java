package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.FrontDesk;
import com.rapidattendencesystem.project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrontDeskRepo extends JpaRepository<FrontDesk, Integer> {
    FrontDesk findFrontDeskByFcode(String mcode);
}
