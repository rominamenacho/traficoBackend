package com.nuebus.repository;

import com.nuebus.model.MailConfig;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Valeria
 */

@RepositoryRestResource(path="mailSenders")
public interface MailSenderRepository extends PagingAndSortingRepository<MailConfig, String>{
    
}
