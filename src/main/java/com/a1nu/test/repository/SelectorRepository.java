package com.a1nu.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a1nu.test.entity.Selector;

@Repository
public interface SelectorRepository extends JpaRepository<Selector, Integer> {

}
