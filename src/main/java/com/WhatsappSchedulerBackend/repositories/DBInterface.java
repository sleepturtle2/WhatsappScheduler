package com.WhatsappSchedulerBackend.repositories;

import com.WhatsappSchedulerBackend.model.entities.Entities;
import org.springframework.stereotype.Repository;

@Repository
public interface DBInterface {
    public int insert(Entities entity);
    public int update(Entities entity);
    public int delete(Entities entity);
}
