package com.fabiomsouza.scriptmanager.repository;

import com.fabiomsouza.scriptmanager.domain.Scene;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SceneRepository extends CrudRepository<Scene, Long> {
    public List<Scene> findAll();
}
