package store.esgseed.api.soccer.team;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.esgseed.api.soccer.common.domain.Messenger;


@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Messenger save(TeamModel team) {
        Team entity = new Team();
        entity.setRegionName(team.getRegionName());
        entity.setTeamName(team.getTeamName());
        entity.setETeamName(team.getETeamName());
        entity.setOrigYyyy(team.getOrigYyyy());
        entity.setZipCode1(team.getZipCode1());
        entity.setZipCode2(team.getZipCode2());
        entity.setAddress(team.getAddress());
        entity.setDdd(team.getDdd());
        entity.setTel(team.getTel());
        entity.setFax(team.getFax());
        entity.setHomepage(team.getHomepage());
        entity.setOwner(team.getOwner());
        teamRepository.save(entity);
        return Messenger.builder().code(200).message("?�공").build();
    }

    @Override
    public Messenger delete(Long id) {
        teamRepository.deleteById(id);
        return Messenger.builder().code(200).message("?�공").build();
    }

    @Override
    public Messenger update(TeamModel team) {
        Team entity = new Team();
        entity.setRegionName(team.getRegionName());
        entity.setTeamName(team.getTeamName());
        entity.setETeamName(team.getETeamName());
        entity.setOrigYyyy(team.getOrigYyyy());
        entity.setZipCode1(team.getZipCode1());
        entity.setZipCode2(team.getZipCode2());
        entity.setAddress(team.getAddress());
        entity.setDdd(team.getDdd());
        entity.setTel(team.getTel());
        entity.setFax(team.getFax());
        entity.setHomepage(team.getHomepage());
        entity.setOwner(team.getOwner());
        teamRepository.save(entity);
        return Messenger.builder().code(200).message("?�공").build();
    }

    @Override
    public Messenger findById(Long id) {
        teamRepository.findById(id);
        return Messenger.builder().code(200).message("?�공").build();
    }

    @Override
    public Messenger findAll() {
        teamRepository.findAll();
        return Messenger.builder().code(200).message("?�공").build();
    }
}
