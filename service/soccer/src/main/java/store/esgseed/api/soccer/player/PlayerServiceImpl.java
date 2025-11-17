package store.esgseed.api.soccer.player;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.esgseed.api.soccer.common.domain.Messenger;


@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Messenger save(PlayerModel player) {
        Player entity = new Player();
        entity.setPlayerName(player.getPlayerName());
        entity.setEPlayerName(player.getEPlayerName());
        entity.setNickname(player.getNickname());
        entity.setJoinYyyy(player.getJoinYyyy());
        entity.setPosition(player.getPosition());
        entity.setBackNo(player.getBackNo());
        entity.setNation(player.getNation());
        entity.setBirthDate(player.getBirthDate());
        entity.setSolar(player.getSolar());
        entity.setHeight(player.getHeight());
        entity.setWeight(player.getWeight());
        playerRepository.save(entity);
        return Messenger.builder().code(200).message("성공").build();
    }

    @Override
    public Messenger delete(Long id) {
        playerRepository.delete(id);
        return Messenger.builder().code(200).message("성공").build();
    }

    @Override
    public Messenger update(PlayerModel player) {
        Player entity = new Player();
        entity.setPlayerName(player.getPlayerName());
        entity.setEPlayerName(player.getEPlayerName());
        entity.setNickname(player.getNickname());
        entity.setJoinYyyy(player.getJoinYyyy());
        entity.setPosition(player.getPosition());
        entity.setBackNo(player.getBackNo());
        entity.setNation(player.getNation());
        entity.setBirthDate(player.getBirthDate());
        entity.setSolar(player.getSolar());
        entity.setHeight(player.getHeight());
        entity.setWeight(player.getWeight());
        playerRepository.save(entity);
        return Messenger.builder().code(200).message("성공").build();
    }

    @Override
    public Messenger findById(Long id) {
        playerRepository.findById(id);
        return Messenger.builder().code(200).message("성공").build();
    }

    @Override
    public Messenger findAll() {
        playerRepository.findAll();
        return Messenger.builder().code(200).message("성공").build();
    }
}
