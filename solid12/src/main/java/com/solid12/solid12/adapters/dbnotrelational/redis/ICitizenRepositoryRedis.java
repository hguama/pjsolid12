package com.solid12.solid12.adapters.dbnotrelational.redis;

import org.springframework.data.repository.CrudRepository;


public interface ICitizenRepositoryRedis extends CrudRepository<CitizenEntityRedis, String> {
}
