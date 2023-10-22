package com.example.bankexample.repository;

import com.example.bankexample.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("select c from Client c where c.clientStatus = 'ACTIVE'")
    List<Client> findAllActiveClients();

    //todo
    // сделать query запрос по статусу
//    List<Client> findClientsByClientStatus(String status);
}
