package com.ssh.greenthumb.api.dao.user;

import com.ssh.greenthumb.api.domain.user.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {

}