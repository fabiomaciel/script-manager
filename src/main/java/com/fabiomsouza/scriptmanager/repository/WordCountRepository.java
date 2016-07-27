package com.fabiomsouza.scriptmanager.repository;

import com.fabiomsouza.scriptmanager.domain.WordCount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordCountRepository extends CrudRepository<WordCount, Long> {
    List<WordCount> findTop10ByCharacterIdOrderByCountDesc(Long characterId);
}
