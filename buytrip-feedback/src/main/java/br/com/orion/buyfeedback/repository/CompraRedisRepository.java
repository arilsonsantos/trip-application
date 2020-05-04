package br.com.orion.buyfeedback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.orion.buyfeedback.model.CompraRedis;

/**
 * CompraRedisRepository
 */
@Repository
public interface CompraRedisRepository extends CrudRepository<CompraRedis, String> {

}