package com.example.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	public Issue findById(long id);
	public Issue findByCreator(long creator);
	public Issue findByTruckAssigned(long truckAssigned);
}
