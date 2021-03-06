package presentation.demo.services.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import presentation.demo.models.bindmodels.PracticeBindModel;
import presentation.demo.models.entities.Practice;
import presentation.demo.models.viewmodels.PracticeDetailsModel;
import presentation.demo.models.viewmodels.PracticeViewModel;
import presentation.demo.repositories.PracticeRepository;
import presentation.demo.services.PracticeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository practiceRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public PracticeServiceImpl(PracticeRepository practiceRepository, ModelMapper mapper, PasswordEncoder encoder) {
        this.practiceRepository = practiceRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public List<String> getAllActivePractice() {
        return this.practiceRepository.getAllActivePractice();
    }

    @Override
    public List<String> getAllPractices() {
        return this.practiceRepository.getAllPractice();
    }

    @Override
    public void deactivate(String name) {
        this.practiceRepository.deactivate(name);
    }

    @Override
    public void activate(String name) {
       this.practiceRepository.activate(name);
    }

    @Override
    public Practice getByName(String name) {
        return this.practiceRepository.findByName(name);
    }

    @Override
    public PracticeViewModel addPractice(PracticeBindModel model) {
        Practice practice = this.mapper.map(model, Practice.class);
        practice.setCreatedOn(LocalDateTime.now());
        practice.setActive(true);
        return this.mapper.map(this.practiceRepository.save(practice),PracticeViewModel.class);
    }

    @Override
    public PracticeDetailsModel getPracticeByName(String name) {
        return this.mapper.map(this.practiceRepository.findByName(name),PracticeDetailsModel.class);
    }
}
