package br.com.authenticator.resource.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT u.id, \n" +
            "u.account_non_expired, \n" +
            "u.account_non_locked, \n" +
            "u.credentials_non_expired, \n" +
            "u.enabled, \n" +
            "u.password, \n" +
            "u.username, \n" +
            "FROM tbl_user u WHERE u.id = :id", nativeQuery = true)
    User findByUserId(@Param("id") Long id);

    @Query(value = "SELECT " +
            "CASE WHEN COUNT(id) > 0 " +
            "THEN TRUE " +
            "ELSE FALSE END " +
            "FROM tbl_user u WHERE u.username = :username", nativeQuery = true)
    boolean isUserExist(@Param("username") String username);

}