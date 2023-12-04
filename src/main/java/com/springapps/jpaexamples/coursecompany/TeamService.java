package com.springapps.jpaexamples.coursecompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Team addStudentToTeam(User student, Long teamId) throws Exception {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new Exception("team not found"));
        student.setTeam(team);
        team.getParticipants().add(student);
        return teamRepository.save(team);
    }
}
