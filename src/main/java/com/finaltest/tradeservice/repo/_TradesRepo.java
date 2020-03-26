package com.finaltest.tradeservice.repo;

import com.finaltest.tradeservice.model.TradesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _TradesRepo extends CrudRepository<TradesModel, Long> {
}
