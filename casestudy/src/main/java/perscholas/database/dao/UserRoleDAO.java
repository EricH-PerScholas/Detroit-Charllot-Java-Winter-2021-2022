package perscholas.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import perscholas.database.entity.User;
import perscholas.database.entity.UserRole;

import java.util.List;


public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    // to add ADMIN access to any user use this SQL and change 7 to the user_id you want to give access to.
    // insert into user_roles ( user_id, user_role ) values ( 7 , 'ADMIN' );

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(@Param("userId")  Integer userId);

}