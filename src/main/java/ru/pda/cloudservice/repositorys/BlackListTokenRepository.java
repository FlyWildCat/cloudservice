package ru.pda.cloudservice.repositorys;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pda.cloudservice.entitys.Token;

@Repository
public interface BlackListTokenRepository extends CrudRepository<Token, Id> {
}
