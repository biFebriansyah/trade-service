package com.finaltest.tradeservice.repo;

import com.finaltest.tradeservice.model.TradesLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _TradesLogs extends CrudRepository<TradesLog, Integer> {
}
