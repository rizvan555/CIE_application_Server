package com.rizvankarimov.cie_app.repository;

import com.rizvankarimov.cie_app.entity.Company;
import com.rizvankarimov.cie_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  }

