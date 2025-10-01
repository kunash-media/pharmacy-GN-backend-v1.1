package com.ph.Pharmacy.repository;
import com.ph.Pharmacy.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> findByEmail(String email);

    Optional<AdminEntity> findByPhoneNumber(String phoneNumber);

    List<AdminEntity> findByFirstName(String firstName);

    List<AdminEntity> findByLastName(String lastName);

    @Query("SELECT a FROM AdminEntity a WHERE a.firstName = :firstName AND a.lastName = :lastName")
    List<AdminEntity> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT a FROM AdminEntity a WHERE a.email LIKE %:domain%")
    List<AdminEntity> findByEmailDomain(@Param("domain") String domain);

    @Query("SELECT a FROM AdminEntity a WHERE a.phoneNumber LIKE :pattern")
    List<AdminEntity> findByPhoneNumberPattern(@Param("pattern") String pattern);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

}

