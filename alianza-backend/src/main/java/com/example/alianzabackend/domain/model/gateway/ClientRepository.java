package com.example.alianzabackend.domain.model.gateway;
import com.example.alianzabackend.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findBySharedKey(String sharedKey);

    @Query("SELECT c FROM Client c WHERE " +
            "(:name IS NULL OR c.name = :name) AND " +
            "(:email IS NULL OR c.email = :email) AND " +
            "(:phone IS NULL OR c.phone = :phone) AND " +
            "(:startDate IS NULL OR c.startDate = :startDate) AND " +
            "(:endDate IS NULL OR c.endDate = :endDate)")
    List<Client> searchClients(
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
