package com.ssh.greenthumb.dao.user;

import com.ssh.greenthumb.domain.user.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {

}