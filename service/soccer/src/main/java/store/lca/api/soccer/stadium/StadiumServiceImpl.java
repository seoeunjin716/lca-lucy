package store.lca.api.soccer.stadium;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.lca.api.soccer.common.domain.Messenger;


@Service
@RequiredArgsConstructor
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;

    @Override
    public Messenger save(StadiumModel stadium) {
        Stadium entity = new Stadium();

        entity.setStadiumName(stadium.getStadiumName());
        entity.setHometeamUk(stadium.getHometeamUk());
        entity.setSeatCount(stadium.getSeatCount());
        entity.setAddress(stadium.getAddress());
        entity.setDdd(stadium.getDdd());
        entity.setTel(stadium.getTel());
        stadiumRepository.save(entity);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger delete(Long id) {
        stadiumRepository.deleteById(id);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger update(StadiumModel stadium) {
        Stadium entity = new Stadium();
        entity.setStadiumName(stadium.getStadiumName());
        entity.setHometeamUk(stadium.getHometeamUk());
        entity.setSeatCount(stadium.getSeatCount());
        entity.setAddress(stadium.getAddress());
        entity.setDdd(stadium.getDdd());
        entity.setTel(stadium.getTel());
        stadiumRepository.save(entity);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger findById(Long id) {
        stadiumRepository.findById(id);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger findAll() {
        stadiumRepository.findAll();
        return Messenger.builder().code(200).message("?标车").build();
    }
}
