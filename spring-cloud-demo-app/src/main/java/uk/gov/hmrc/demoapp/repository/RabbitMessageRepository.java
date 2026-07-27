package uk.gov.hmrc.demoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.gov.hmrc.demoapp.model.RabbitMessage;

import java.util.List;

@Repository
public interface RabbitMessageRepository extends CrudRepository<RabbitMessage, Long> {

    List<RabbitMessage> findAll();

    List<RabbitMessage> findAllByOrderByDateAddedDesc();

}
