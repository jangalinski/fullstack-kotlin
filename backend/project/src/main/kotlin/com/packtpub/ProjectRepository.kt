package com.packtpub

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : CrudRepository<Project, Long>{
    fun findByOwner(owner: String): List<Project>

    @Query("SELECT distinct owner FROM Project")
    fun retrieveAllOwners(): List<String>

    @Query("""
        SELECT
        new com.packtpub.ProjectView(name, url, owner, language)
        FROM Project
    """)
    fun retrieveAllProjectsForView(): List<ProjectView>
}