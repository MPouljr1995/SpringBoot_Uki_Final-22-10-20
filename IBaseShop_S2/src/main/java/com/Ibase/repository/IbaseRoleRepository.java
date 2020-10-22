package com.Ibase.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Ibase.model.IbaseERole;
import com.Ibase.model.IbaseRole;

@Repository
public interface IbaseRoleRepository extends MongoRepository<IbaseRole, String>{
	Optional<IbaseRole> findByName(IbaseERole name);
}
