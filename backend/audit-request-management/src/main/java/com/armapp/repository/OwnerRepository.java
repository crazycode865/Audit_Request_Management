package com.armapp.repository;


import com.armapp.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner getByOwnerUserId(String ownerUserId);
}
